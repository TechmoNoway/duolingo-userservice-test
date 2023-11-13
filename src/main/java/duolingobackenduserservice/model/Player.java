package duolingobackenduserservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    private String id;
    private String userId;
    private String currentLevel;
    private String language;
    private Integer expPoint;
    private Integer score;
    private Integer heart;
    private String lastUpdateDate;
}
