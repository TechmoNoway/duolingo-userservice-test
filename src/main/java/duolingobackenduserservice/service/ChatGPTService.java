package duolingobackenduserservice.service;

import duolingobackenduserservice.dto.ChatGPTResponse;

import java.util.List;

public interface ChatGPTService {
    String getChatGPTRespone(String prompt);

    List<String> getAnswersForQuestionSet(List<String> request);
}
