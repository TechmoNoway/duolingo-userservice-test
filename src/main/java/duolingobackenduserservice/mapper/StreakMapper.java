package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Streak;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StreakMapper {
    List<Streak> getAllStreaks();
    void insertStreak(Streak streak);
}
