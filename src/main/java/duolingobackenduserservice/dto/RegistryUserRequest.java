package duolingobackenduserservice.dto;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistryUserRequest {
    private String id;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private String social;
    private String createdAt;
    private String updatedAt;
}
