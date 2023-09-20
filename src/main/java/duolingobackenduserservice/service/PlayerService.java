package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    void insertPlayer(Player player);
}
