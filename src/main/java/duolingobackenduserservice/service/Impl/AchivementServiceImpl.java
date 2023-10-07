 package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.AchivementMapper;
import duolingobackenduserservice.model.Achivement;
import duolingobackenduserservice.service.AchivementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchivementServiceImpl implements AchivementService {

    @Autowired
    AchivementMapper achivementMapper;

    @Override
    public List<Achivement> getAllAchivements() {

        return achivementMapper.getAllAchivements();
    }

    @Override
    public void insertAchivement(Achivement achivement) {

        achivementMapper.insertAchivement(achivement);

    }
}
