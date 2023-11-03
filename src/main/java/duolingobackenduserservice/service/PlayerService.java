package duolingobackenduserservice.service;


import duolingobackenduserservice.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    boolean insertPlayer(Player player);

    String updatePlayer(Player player);
}
