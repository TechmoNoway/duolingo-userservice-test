package duolingobackenduserservice.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Streak {
    private String id;
    private String playerId;
    private Date startDate;
    private Date endDate;
    private Boolean isEnd;
}
