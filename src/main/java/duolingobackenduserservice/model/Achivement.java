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
    private Long score;
    private String sourceId;
    private String title;
    private Date createdAt;
    private Date updatedAt;
}
