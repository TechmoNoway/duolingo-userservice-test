package duolingobackenduserservice.mapper;

import duolingobackenduserservice.dto.Rank;
import duolingobackenduserservice.model.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerMapper {
    List<Player> getAllPlayers();
    void insertPlayer(Player player);

    Player getPlayerByUserId(@Param("userId") String userId, @Param("language") String language);

    void updatePlayer(Player player);

    List<Rank> getRankLimit5();
}
