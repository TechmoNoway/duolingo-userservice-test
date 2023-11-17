package duolingobackenduserservice.thread;

import duolingobackenduserservice.service.ChatGPTService;

import java.util.ArrayList;
import java.util.Arrays;

public class AddAnswerGPTThread implements Runnable{
    private String answer;
    private final ChatGPTService chatGPTService;

    private String question;

    public AddAnswerGPTThread(ChatGPTService chatGPTService, String question) {
        this.chatGPTService = chatGPTService;
        this.question = question;
    }


    @Override
    public void run() {
        String answer = chatGPTService.getChatGPTRespone(question);
        setAnswer(answer);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
