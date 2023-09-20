package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Achivement;

import java.util.List;

public interface AchivementService {
    List<Achivement> getAllAchivements();
    void insertAchivement(Achivement achivement);
}
