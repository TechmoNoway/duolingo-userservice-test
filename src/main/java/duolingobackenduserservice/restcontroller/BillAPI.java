package duolingobackenduserservice.restcontroller;


import duolingobackenduserservice.model.Bill;
import duolingobackenduserservice.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/bill")
public class BillAPI {

    @Autowired
    BillService billService;

    @GetMapping("/getallbills")
    public ResponseEntity<?> doGetAllBills(){

        HashMap<String, Object> result = new HashMap<>();

        try {
            result.put("success", true);
            result.put("message", "Success to call api doGetAllBills");
            result.put("data", billService.getAllBills());
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doGetAllBills");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/insertbill")
    public ResponseEntity<?> doInsertBill(@RequestBody Bill bill){
        HashMap<String, Object> result = new HashMap<>();

        try {
            billService.insertBill(bill);
            result.put("success", true);
            result.put("message", "Success to call api doInsertBill");
            result.put("data", bill);
        }catch (Exception e){
            result.put("success", false);
            result.put("message", "Fail to call api doInsertBill");
            result.put("data", null);
        }

        return ResponseEntity.ok(result);
    }

}
