package duolingobackenduserservice.service.Impl;

import duolingobackenduserservice.dto.ChatGPTMessage;
import duolingobackenduserservice.dto.ChatGPTRequest;
import duolingobackenduserservice.dto.ChatGPTResponse;
import duolingobackenduserservice.service.ChatGPTService;
import duolingobackenduserservice.thread.AddAnswerGPTThread;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    private static final String OPEN_API_CHAT_ENDPOINT = "https://api.openai.com/v1/chat/completions";



    @Override
    public String getChatGPTRespone(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        ChatGPTRequest request = new ChatGPTRequest();
        request.setModel(model);
        request.setMessages(List.of(new ChatGPTMessage("user", prompt)));

        HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(request, headers);

        RestTemplate template = new RestTemplate();

        ChatGPTResponse answerResponse = template.postForObject(OPEN_API_CHAT_ENDPOINT, entity, ChatGPTResponse.class);
        String answer = answerResponse.getChoices().get(0).getMessage().getContent();

        return answer;
    }

    @Override
    public List<String> getAnswersForQuestionSet(List<String> request) {
        List<String> resultList = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < request.size(); i++) {

            AddAnswerGPTThread addAnswerGPTThread = new AddAnswerGPTThread(this, request.get(i));
            Thread thread = new Thread(addAnswerGPTThread);
            threads.add(thread);
            thread.start();
        }

        try {
            // Wait for all threads to finish
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return resultList;
    }


}
