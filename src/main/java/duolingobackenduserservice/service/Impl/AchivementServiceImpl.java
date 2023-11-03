 package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.AchivementMapper;
import duolingobackenduserservice.model.Achivement;
import duolingobackenduserservice.service.AchivementService;
import duolingobackenduserservice.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchivementServiceImpl implements AchivementService {

    @Autowired
    AchivementMapper achivementMapper;


    @Autowired
    CommonService commonService;
    @Override
    public List<Achivement> getAllAchivements(String playerId) {

        List<Achivement> playerAchievement = achivementMapper.getAchivementsByPlayerId(playerId);

        return playerAchievement;
    }

    @Override
    public String insertAchivement(Achivement achivement) {

        String id = commonService.generateRandomNumber(10);
        String createdAt = commonService.createCurrentDate();
        achivement.setId(id);
        achivement.setCreatedAt(createdAt);
        achivementMapper.insertAchivement(achivement);

        return "Achievement is added successfully";

    }
}
