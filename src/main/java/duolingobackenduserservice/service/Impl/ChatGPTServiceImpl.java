package duolingobackenduserservice.service.Impl;

import duolingobackenduserservice.dto.ChatGPTMessage;
import duolingobackenduserservice.dto.ChatGPTRequest;
import duolingobackenduserservice.dto.ChatGPTResponse;
import duolingobackenduserservice.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        String question = String.join(",", request);
        String answer = getChatGPTRespone(question);

        List<String> answerSet = List.of(answer.split("\\n\\n"));

        return removeEmptyValue(answerSet);
    }


    public List<String> removeEmptyValue(List<String> data) {
        List<String> newList = new ArrayList<>();

        for (String value : data) {
            if (value != null && !value.trim().isEmpty() && !value.matches("^[\\p{Punct}\\s]+$")) {
                newList.add(value);
            }
        }

        return newList;
    }

}
