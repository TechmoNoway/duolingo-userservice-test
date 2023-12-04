package duolingobackenduserservice.service;

import duolingobackenduserservice.dto.BillDetail;
import duolingobackenduserservice.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAllBills();
    void insertBill(BillDetail bill);
}
