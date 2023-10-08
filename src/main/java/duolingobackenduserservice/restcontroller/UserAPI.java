package duolingobackenduserservice.restcontroller;


import duolingobackenduserservice.dto.AuthenticationResponse;
import duolingobackenduserservice.dto.CheckLoginRequest;
import duolingobackenduserservice.dto.RegistryUserRequest;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.UserService;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
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
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@NonNull HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doInsertUser");
            result.put("data", request.getAttribute("user"));
        }catch (Exception e){
            System.out.println(e);
            result.put("success", false);
            result.put("message", "Fail to call api doInsertUser");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }


    //    This method is similar as register method
    @PostMapping("/insertuser")
    public ResponseEntity<?> doInsertUser(@RequestBody RegistryUserRequest registryUserRequest){
        HashMap<String, Object> result = new HashMap<>();

        try {
            AuthenticationResponse data = userService.register(registryUserRequest);
            result.put("success", true);
            result.put("message", "Success to call api doInsertUser");
            result.put("data", data);
        }catch (Exception e){
            System.out.println(e);
            result.put("success", false);
            result.put("message", "Fail to call api doInsertUser");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

//    This method is similar as login method
    @PostMapping("/checklogin")
    public ResponseEntity<?> doCheckLogin(@RequestBody CheckLoginRequest checkLoginRequest){
        HashMap<String, Object> result = new HashMap<>();

        try {
            AuthenticationResponse data = userService.login(checkLoginRequest);
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
