package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Achivement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchivementMapper {
    List<Achivement> getAllAchivements();
    void insertAchivement(Achivement achivement);

}
