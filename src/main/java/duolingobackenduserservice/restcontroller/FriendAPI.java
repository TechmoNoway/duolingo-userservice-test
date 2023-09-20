package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.model.Friend;
import duolingobackenduserservice.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/friend")
public class FriendAPI {

    @Autowired
    FriendService friendService;

    @GetMapping("/getallfriend")
    public ResponseEntity<?> doGetAllFriends(){

        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doGetAllFriends");
            result.put("data", friendService.getAllFriends());
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
            friendService.insertFriend(friend);
            result.put("success", true);
            result.put("message", "Success to call api doInsertFriend");
            result.put("data", friend);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertFriend");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

}
