package duolingobackenduserservice.service;

import duolingobackenduserservice.dto.ChatGPTResponse;

public interface ChatGPTService {
    ChatGPTResponse getChatGPTRespone(String prompt);
}
