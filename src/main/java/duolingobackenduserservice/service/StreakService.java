package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Streak;

import java.util.List;

public interface StreakService {

    List<Streak> getAllStreaks();

    String insertStreak(Streak streak);

    List<Streak> getStreakByUserId(String userId, String language);

    String checkStreakAndEndStreak(Streak streak, String userId, String language);


}
