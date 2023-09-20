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
    private Long currentLevel;
    private String language;
    private Long expPoint;
}
