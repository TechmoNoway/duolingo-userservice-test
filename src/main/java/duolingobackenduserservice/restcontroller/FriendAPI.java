package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.model.Friend;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/friend")
public class FriendAPI {

    @Autowired
    FriendService friendService;

    @GetMapping("/getallfriend/{userId}")
    public ResponseEntity<?> doGetAllFriends(@PathVariable String userId){

        HashMap<String, Object> result = new HashMap<>();

        try {
            List<User> data = friendService.getAllFriends(userId);
            result.put("success", true);
            result.put("message", "Success to call api doGetAllFriends");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllFriends");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/insertfriend")
    public ResponseEntity<?> doInsertFriend(@RequestBody Friend friend){
        HashMap<String, Object> result = new HashMap<>();

        try {
            String data = friendService.insertFriend(friend);
            result.put("success", true);
            result.put("message", "Success to call api doInsertFriend");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertFriend");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/unfriend")
    public ResponseEntity<?> unfriend(@RequestBody Friend friend){
        HashMap<String, Object> result = new HashMap<>();

        try {
            String data = friendService.unfriend(friend);
            result.put("success", true);
            result.put("message", "Success to call api doInsertFriend");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertFriend");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

}
