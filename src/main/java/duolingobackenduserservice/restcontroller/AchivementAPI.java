package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.model.Achivement;
import duolingobackenduserservice.service.AchivementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/achivement")
public class AchivementAPI {

    @Autowired
    AchivementService achivementService;

    @GetMapping("/getallachivements")
    public ResponseEntity<?> doGetAllAchivements(){

        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doGetAllAchivements");
            result.put("data", achivementService.getAllAchivements());
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllAchivements");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/insertachivement")
    public ResponseEntity<?> doInsertAchivement(@RequestBody Achivement achivement){
        HashMap<String, Object> result = new HashMap<>();

        try {
            achivementService.insertAchivement(achivement);
            result.put("success", true);
            result.put("message", "Success to call api doInsertAchivement");
            result.put("data", achivement);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertAchivement");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

}
