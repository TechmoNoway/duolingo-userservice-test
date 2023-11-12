package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.StreakMapper;
import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.model.Streak;
import duolingobackenduserservice.service.CommonService;
import duolingobackenduserservice.service.PlayerService;
import duolingobackenduserservice.service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreakUserService implements StreakService {

    @Autowired
    StreakMapper streakMapper;

    @Autowired
    PlayerService playerService;

    @Autowired
    CommonService commonService;

    @Override
    public List<Streak> getAllStreaks() {
        return streakMapper.getAllStreaks();
    }

    @Override
    public String insertStreak(Streak streak) {
        List<Streak> checkedStreak = streakMapper.getStreakByPlayerId(streak.getPlayerId());

        boolean checkRunningStreak = checkedStreak.stream()
                .anyMatch(a -> !a.getIsEnd());

        if(checkRunningStreak) {
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
    public List<Streak> getStreakByUserId(String userId, String language) {
        Player player = playerService.getPlayerByUserId(userId, language);

        return streakMapper.getStreakByPlayerId(player.getId());
    }

    @Override
    public String checkStreakAndEndStreak(Streak streak, String userId, String language) {
        List<Streak> checkedStreak = streakMapper.getStreakByPlayerId(streak.getPlayerId());
        Player player = playerService.getPlayerByUserId(userId, language);
        boolean checkRunningStreak = checkedStreak.stream()
                .anyMatch(a -> !a.getIsEnd());
        if(!checkRunningStreak) {
            return "This player don't have any streak is running";
        }

        String currentDate = commonService.createCurrentDate();
        boolean checkedOverTwoDayInStreak = commonService.areTwoDatesMoreThanOneDayApart(player.getLastUpdateDate(), currentDate);
        if(checkedOverTwoDayInStreak) {
            String endDate = commonService.createCurrentDate();
            streak.setEndDate(endDate);
            streak.setIsEnd(true);
            streakMapper.updateStreak(streak);
            return "End streak is successfully";
        }

        return "All of them is normal";
    }
}
