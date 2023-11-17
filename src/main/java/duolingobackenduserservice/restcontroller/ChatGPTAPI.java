package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.dto.ChatGPTResponse;
import duolingobackenduserservice.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("/api/chatgpt")
@CrossOrigin("*")
public class ChatGPTAPI {
    @Autowired
    private ChatGPTService chatGPTService;

    @GetMapping("/chatbot")
    public ResponseEntity<?> chatBot(@RequestParam("query") String query){

        HashMap<String, Object> result = new HashMap<>();

        try {
            ChatGPTResponse response = chatGPTService.getChatGPTRespone(query);
            String data = response.getChoices().get(0).getMessage().getContent();
            result.put("success", true);
            result.put("message", "Success to call api doGetAllBills");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllBills");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }
}
