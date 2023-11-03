package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.model.Streak;
import duolingobackenduserservice.service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/streak")
public class StreakAPI {

    @Autowired
    StreakService streakService;

    @GetMapping("/getallstreaks")
    public ResponseEntity<?> doGetAllStreaks(){

        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doGetAllStreaks");
            result.put("data", streakService.getAllStreaks());
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllStreaks");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/insertstreak")
    public ResponseEntity<?> doInsertUser(@RequestBody Streak streak){
        HashMap<String, Object> result = new HashMap<>();

        try {
            String data = streakService.insertStreak(streak);
            result.put("success", true);
            result.put("message", "Success to call api doInsertStreak");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertStreak");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }
    @PutMapping("/endStreak")
    public ResponseEntity<?> endStreak(@RequestBody Streak streak){
        HashMap<String, Object> result = new HashMap<>();

        try {
            String data = streakService.endStreak(streak);
            result.put("success", true);
            result.put("message", "Success to call api doInsertUser");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertUser");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

}
