package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.PlayerMapper;
import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.service.CommonService;
import duolingobackenduserservice.service.PlayerService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        player.setId(id);
        playerMapper.insertPlayer(player);

        return true;
    }

    @Override
    public String updatePlayer(Player player) {
        Player checkedPlayer = playerMapper.getPlayerByUserId(player.getUserId(), player.getLanguage());
        if(checkedPlayer == null){
            return "This player is not exist";
        }
        player.setId(checkedPlayer.getId());

        playerMapper.updatePlayer(player);
        return "Update player is successfully";
    }
}
