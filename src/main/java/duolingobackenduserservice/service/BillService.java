package duolingobackenduserservice.service;

import duolingobackenduserservice.model.Bill;

import java.util.List;

public interface BillService {
    List<Bill> getAllBills();
    void insertBill(Bill bill);
}
