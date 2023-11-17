package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.dto.ChatGPTResponse;
import duolingobackenduserservice.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
            String data = chatGPTService.getChatGPTRespone(query);
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

    @PostMapping("/solverAnswerQueston")
    public ResponseEntity<?> solverAnswerQueston(@RequestBody List<String> request){

        HashMap<String, Object> result = new HashMap<>();

        try {
            List<String> data = chatGPTService.getAnswersForQuestionSet(request);
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
