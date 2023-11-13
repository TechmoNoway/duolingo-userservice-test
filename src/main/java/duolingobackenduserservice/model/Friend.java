package duolingobackenduserservice.model;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend {
    private String id;
    private String userId;
    private String friendId;
    private String startDate;
}
