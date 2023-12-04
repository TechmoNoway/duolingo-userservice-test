package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillMapper {
    List<Bill> getAllBills();

    List<Bill> getBillsByUserId(@Param("userId") String userId);
    void insertBills(Bill bill);

    void updateBill(Bill bill);

}

