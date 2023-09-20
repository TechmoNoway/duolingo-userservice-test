package duolingobackenduserservice.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private String roleId;
    private Date createdAt;
    private Date updatedAt;
}
