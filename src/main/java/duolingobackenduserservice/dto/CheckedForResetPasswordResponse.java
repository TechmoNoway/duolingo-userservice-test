package duolingobackenduserservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CheckedForResetPasswordResponse {
    private UserDetails userDetails;
    private boolean isExpired;
}
