package duolingobackenduserservice.dto;

import duolingobackenduserservice.model.Player;
import duolingobackenduserservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Rank {
    private String playerId;
    private String userId;
    private String avatar;
    private String username;
    private String email;
    private String score;
    private String expPoint;
    private String currentLevel;
}
