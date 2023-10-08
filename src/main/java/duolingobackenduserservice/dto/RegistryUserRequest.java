package duolingobackenduserservice.dto;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistryUserRequest {
    private String username;
    private String email;
    private String password;
    private String avatar;
    private String social;
    private Date createdAt;
    private Date updatedAt;
}
