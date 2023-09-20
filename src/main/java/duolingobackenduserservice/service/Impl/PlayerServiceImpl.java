package duolingobackenduserservice.service.Impl;


import duolingobackenduserservice.mapper.PlayerMapper;
import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.service.PlayerService;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerMapper playerMapper;

    @Override
    public List<Player> getAllPlayers() {
        return playerMapper.getAllPlayers();
    }

    @Override
    public void insertPlayer(Player player) {
        playerMapper.insertPlayer(player);
    }
}
