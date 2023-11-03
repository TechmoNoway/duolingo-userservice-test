package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.StreakMapper;
import duolingobackenduserservice.model.Streak;
import duolingobackenduserservice.service.CommonService;
import duolingobackenduserservice.service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreakUserService implements StreakService {

    @Autowired
    StreakMapper streakMapper;

    @Autowired
    CommonService commonService;

    @Override
    public List<Streak> getAllStreaks() {
        return streakMapper.getAllStreaks();
    }

    @Override
    public String insertStreak(Streak streak) {
        Streak checkedStreak = streakMapper.getStreakByPlayerId(streak.getPlayerId());
        if(checkedStreak != null) {
            return "This player is having a streak is running";
        }
        String id = commonService.generateRandomNumber(10);
        String startDate = commonService.createCurrentDate();

        streak.setId(id);
        streak.setStartDate(startDate);
        streak.setIsEnd(false);

        streakMapper.insertStreak(streak);

        return "This streak have added";
    }

    @Override
    public String endStreak(Streak streak) {
        Streak checkedStreak = streakMapper.getStreakByPlayerId(streak.getPlayerId());
        if(checkedStreak == null){
            return "This streak is not exist";
        }

        String endDate = commonService.createCurrentDate();
        streak.setEndDate(endDate);
        streak.setIsEnd(true);

        streak.setId(checkedStreak.getId());
        streakMapper.updateStreak(streak);
        return "End streak is successfully";
    }
}
