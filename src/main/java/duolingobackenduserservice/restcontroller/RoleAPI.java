package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.model.Role;
import duolingobackenduserservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/role")
public class RoleAPI {

    @Autowired
    RoleService roleService;

    @GetMapping("/getallroles")
    public ResponseEntity<?> doGetAllRoles(){

        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doGetAllRoles");
            result.put("data", roleService.getAllRoles());
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllRoles");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/insertrole")
    public ResponseEntity<?> doInsertRole(@RequestBody Role role){
        HashMap<String, Object> result = new HashMap<>();

        try {
            roleService.insertRole(role);
            result.put("success", true);
            result.put("message", "Success to call api doInsertRole");
            result.put("data", role);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertRole");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

}
