package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.dto.AuthenticationResponse;
import duolingobackenduserservice.dto.Rank;
import duolingobackenduserservice.dto.UpdatedRequest;
import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.PlayerService;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
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

    @GetMapping("/getplayer/{language}")
    public ResponseEntity<?> getPlayer(@PathVariable String language,@NonNull HttpServletRequest request){

        HashMap<String, Object> result = new HashMap<>();

        try {
            User user = (User) request.getAttribute("user");
            Player data = playerService.getPlayerByUserId(user.getId(), language);
            result.put("success", true);
            result.put("message", "Success to call api doGetAllPlayers");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllPlayers");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getRanks")
    public ResponseEntity<?> getRanks(){

        HashMap<String, Object> result = new HashMap<>();

        try {
            List<Rank> data = playerService.getLeaderBoard();
            result.put("success", true);
            result.put("message", "Success to call api doGetAllPlayers");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllPlayers");
            result.put("data", null);
            e.printStackTrace();
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

    @PutMapping("/updatePlayer")
    public ResponseEntity<?> updateUser(@RequestBody Player player){
        HashMap<String, Object> result = new HashMap<>();

        try {
            String data = playerService.updatePlayer(player);
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
