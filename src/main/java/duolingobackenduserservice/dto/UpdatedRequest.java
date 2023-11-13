package duolingobackenduserservice.dto;

import duolingobackenduserservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedRequest extends User {
    private String oldPassword;
}
