package duolingobackenduserservice.restcontroller;


import duolingobackenduserservice.dto.*;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.UserService;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
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


    @GetMapping("/getUserExceptUserId")
    public ResponseEntity<?> getUserExceptUserId(@NonNull HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            User user = (User) request.getAttribute("user");
            List<User> users = userService.getUserExceptPlayerId(user.getId());
            result.put("success", true);
            result.put("message", "Success to call api doInsertUser");
            result.put("data", users);
        }catch (Exception e){
            System.out.println(e);
            result.put("success", false);
            result.put("message", "Fail to call api doInsertUser");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/checkTokenForResetPassword/{token}")
    public ResponseEntity<?> checkTokenForResetPassword(@PathVariable String token) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            CheckedForResetPasswordResponse data = userService.checkTokenForResetPassword(token);
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

    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UpdatedRequest user){
        HashMap<String, Object> result = new HashMap<>();

        try {
            AuthenticationResponse data = userService.updateUser(user);
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

    @PostMapping("/sendemailforresetpassword")
    ResponseEntity<?> sendEmailForResetPassword(@RequestBody InputSendEmailData request) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String data = userService.sendEmailForResetPassword(request);
            result.put("success", true);
            result.put("message", "Success to call API GetAllUsers");
            result.put("data", data);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Fail to call API GetAllUsers");
            result.put("data", null);
            e.printStackTrace();
        }
        return ResponseEntity.ok(result);
    }


}
