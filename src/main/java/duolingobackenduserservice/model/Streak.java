package duolingobackenduserservice.model;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Streak {
    private String id;
    private String playerId;
    private String startDate;
    private String endDate;
    public Boolean isEnd;
}
