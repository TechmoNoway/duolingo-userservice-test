package duolingobackenduserservice.restcontroller;

import duolingobackenduserservice.model.Achivement;
import duolingobackenduserservice.service.AchivementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/achivement")
public class AchivementAPI {

    @Autowired
    AchivementService achivementService;

    @GetMapping("/getallachivements/{playerId}")
    public ResponseEntity<?> doGetAllAchivements(@PathVariable String playerId){

        HashMap<String, Object> result = new HashMap<>();

        try {
            List<Achivement> data = achivementService.getAllAchivements(playerId);
            result.put("success", true);
            result.put("message", "Success to call api doGetAllAchivements");
            result.put("data", data);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllAchivements");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/insertachivement")
    public ResponseEntity<?> doInsertAchivement(@RequestBody Achivement achivement){
        HashMap<String, Object> result = new HashMap<>();

        try {
            String data = achivementService.insertAchivement(achivement);
            result.put("success", true);
            result.put("message", "Success to call api doInsertAchivement");
            result.put("data", data);

        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertAchivement");
            result.put("data", null);
            e.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

}
