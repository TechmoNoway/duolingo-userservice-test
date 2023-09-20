package duolingobackenduserservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistryUserRequest {
    private String username;
    private String email;
    private String password;
    private String avatar;
    private Date createdAt;
    private Date updatedAt;
}
