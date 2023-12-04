package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.dto.BillDetail;
import duolingobackenduserservice.mapper.BillMapper;
import duolingobackenduserservice.model.Bill;
import duolingobackenduserservice.service.BillService;
import duolingobackenduserservice.service.CommonService;
import duolingobackenduserservice.service.PaypalService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillMapper billMapper;

    @Autowired
    PaypalService paypalService;

    @Autowired
    CommonService commonService;

    @Override
    public List<Bill> getAllBills() {

        return billMapper.getAllBills();
    }

    @Override
    public void insertBill(BillDetail bill) {
//        paypalService.createPayment(bill);

        String id = commonService.generateRandomNumber(10);
        String createdAt = commonService.createCurrentDate();
        Bill newBill = Bill.builder()
                .id(id)
                .description(bill.getDescription())
                .createdAt(createdAt)
                .price(bill.getTotal())
                .payment(bill.getCurrency())
                .userId(bill.getUserId())
                .title("Transaction at "+createdAt)
                .build();
        billMapper.insertBills(newBill);

    }
}
