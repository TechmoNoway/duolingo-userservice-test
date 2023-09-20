package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.StreakMapper;
import duolingobackenduserservice.model.Streak;
import duolingobackenduserservice.service.StreakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreakUserService implements StreakService {

    @Autowired
    StreakMapper streakMapper;

    @Override
    public List<Streak> getAllStreaks() {
        return streakMapper.getAllStreaks();
    }

    @Override
    public void insertStreak(Streak streak) {
        streakMapper.insertStreak(streak);
    }
}
