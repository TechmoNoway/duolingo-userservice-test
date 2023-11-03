package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Streak;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StreakMapper {
    List<Streak> getAllStreaks();

    Streak getStreakByPlayerId(@Param("playerId") String playerId);
    void insertStreak(Streak streak);

    void updateStreak(Streak streak);
}
