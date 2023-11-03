package duolingobackenduserservice.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Achivement {
    private String id;
    private String playerId;
    private Integer score;
    private String sourceId;
    private String title;
    private String createdAt;
    private String updatedAt;
}
