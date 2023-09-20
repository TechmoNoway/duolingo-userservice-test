package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Bill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {
    List<Bill> getAllBills();
    void insertBills(Bill bill);

}

