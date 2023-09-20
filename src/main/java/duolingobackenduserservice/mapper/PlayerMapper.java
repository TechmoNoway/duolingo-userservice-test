package duolingobackenduserservice.mapper;

import duolingobackenduserservice.model.Player;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlayerMapper {
    List<Player> getAllPlayers();
    void insertPlayer(Player player);
}
