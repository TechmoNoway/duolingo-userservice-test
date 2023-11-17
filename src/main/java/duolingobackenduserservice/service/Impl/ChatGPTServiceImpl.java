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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;



    @Override
    public ChatGPTResponse getChatGPTRespone(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        ChatGPTRequest request = new ChatGPTRequest();
        request.setModel(model);
        request.setMessages(List.of(new ChatGPTMessage("user", prompt)));

        HttpEntity<ChatGPTRequest> entity = new HttpEntity<>(request, headers);

        RestTemplate template = new RestTemplate();
        return template.postForObject(apiURL, entity, ChatGPTResponse.class);
    }
}
