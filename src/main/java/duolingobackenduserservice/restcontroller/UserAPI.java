package duolingobackenduserservice.restcontroller;


import duolingobackenduserservice.dto.CheckLoginRequest;
import duolingobackenduserservice.dto.RegistryUserRequest;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    UserService userService;

    @GetMapping("/getallusers")
    public ResponseEntity<?> doGetAllUsers(){

        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doGetAllUsers");
            result.put("data", userService.getAllUsers());
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllUsers");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/insertuser")
    public ResponseEntity<?> doInsertUser(@RequestBody RegistryUserRequest registryUserRequest){
        HashMap<String, Object> result = new HashMap<>();

        try {
            userService.insertUser(registryUserRequest);
            result.put("success", true);
            result.put("message", "Success to call api doInsertUser");
            result.put("data", registryUserRequest);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertUser");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/checklogin")
    public ResponseEntity<?> doCheckLogin(@RequestBody CheckLoginRequest checkLoginRequest){
        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doInsertUser");
            result.put("data", userService.checkLogin(checkLoginRequest));
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertUser");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

}
