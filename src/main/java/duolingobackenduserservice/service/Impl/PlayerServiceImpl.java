package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.dto.Rank;
import duolingobackenduserservice.mapper.PlayerMapper;
import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.model.User;
import duolingobackenduserservice.service.CommonService;
import duolingobackenduserservice.service.PlayerService;
import duolingobackenduserservice.service.UserService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerMapper playerMapper;

    @Autowired
    CommonService commonService;


    @Override
    public List<Player> getAllPlayers() {
        return playerMapper.getAllPlayers();
    }

    @Override
    public boolean insertPlayer(Player player) {
        Player checkedPlayer = playerMapper.getPlayerByUserId(player.getUserId(), player.getLanguage());
        if(checkedPlayer != null){
            return false;
        }
        String id = commonService.generateRandomNumber(10);
        player.setScore(0);
        player.setId(id);
        player.setCurrentLevel("1");
        player.setExpPoint(0);
        player.setHeart(5);
        playerMapper.insertPlayer(player);

        return true;
    }

    @Override
    public Player getPlayerByUserId(String userId, String language) {
        return playerMapper.getPlayerByUserId(userId, language);
    }

    @Override
    public Player updatePlayer(Player player) {
        Player checkedPlayer = playerMapper.getPlayerByUserId(player.getUserId(), player.getLanguage());
        if(checkedPlayer == null){
            return checkedPlayer;
        }
        String lastUpdateDate = commonService.createCurrentDate();
        player.setId(checkedPlayer.getId());
        player.setLastUpdateDate(lastUpdateDate);

        playerMapper.updatePlayer(player);
        return player;
    }

    @Override
    public List<Rank> getLeaderBoard() {
        List<Rank> ranks = playerMapper.getRankLimit5();
        List<Rank> endRanks = new ArrayList<>();


        for(int i = 0; i < ranks.size(); i++){
            String currentLevel = ranks.get(i).getCurrentLevel();
            String[] levelArr = currentLevel.split(".");
            if(levelArr.length > 1) {
                if(Integer.parseInt(levelArr[1]) < 0) {
                    if(Integer.parseInt(levelArr[2]) < 6) {
                        endRanks.add(ranks.get(i));
                    }
                }
                else {
                    endRanks.add(ranks.get(i));
                }
            }
        }

        return endRanks;
    }
}
