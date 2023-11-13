package duolingobackenduserservice.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckLoginRequest {
    String username;
    String password;
    String email;
}
