package duolingobackenduserservice.service;


import duolingobackenduserservice.dto.Rank;
import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.model.User;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();
    boolean insertPlayer(Player player);

    Player getPlayerByUserId(String userId, String language);

    String updatePlayer(Player player);

    List<Rank> getLeaderBoard();
}
