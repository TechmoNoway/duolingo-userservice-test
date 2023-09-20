package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/player")
public class PlayerAPI {

    @Autowired
    PlayerService playerService;

    @GetMapping("/getallplayers")
    public ResponseEntity<?> doGetAllPlayers(){

        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doGetAllPlayers");
            result.put("data", playerService.getAllPlayers());
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllPlayers");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/insertplayer")
    public ResponseEntity<?> doInsertPlayer(@RequestBody Player player){
        HashMap<String, Object> result = new HashMap<>();

        try {
            playerService.insertPlayer(player);
            result.put("success", true);
            result.put("message", "Success to call api doInsertPlayer");
            result.put("data", player);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertPlayer");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }


}
