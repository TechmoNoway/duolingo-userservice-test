package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Achivement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AchivementMapper {
    List<Achivement> getAllAchivements();

    List<Achivement> getAchivementsByPlayerId(@Param("playerId") String playerId);
    void insertAchivement(Achivement achivement);

}
