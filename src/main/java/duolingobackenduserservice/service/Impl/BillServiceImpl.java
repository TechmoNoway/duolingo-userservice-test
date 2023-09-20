package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.BillMapper;
import duolingobackenduserservice.model.Bill;
import duolingobackenduserservice.service.BillService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Mapper
    BillMapper billMapper;

    @Override
    public List<Bill> getAllBills() {

        return billMapper.getAllBills();
    }

    @Override
    public void insertBill(Bill bill) {

        billMapper.insertBills(bill);

    }
}
