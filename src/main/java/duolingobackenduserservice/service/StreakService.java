package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Streak;

import java.util.List;

public interface StreakService {

    List<Streak> getAllStreaks();
    void insertStreak(Streak streak);

}
