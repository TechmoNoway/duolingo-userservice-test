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
    private Integer currentLevel;
    private String language;
    private Integer expPoint;
}
