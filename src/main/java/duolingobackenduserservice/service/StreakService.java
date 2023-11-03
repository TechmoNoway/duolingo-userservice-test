package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Streak;

import java.util.List;

public interface StreakService {

    List<Streak> getAllStreaks();

    String insertStreak(Streak streak);

    String endStreak(Streak streak);

}
