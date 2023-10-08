package duolingobackenduserservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckLoginRequest {
    String username;
    String password;

    String email;
}
