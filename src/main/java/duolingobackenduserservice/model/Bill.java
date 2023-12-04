package duolingobackenduserservice.model;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bill {
    private String id;
    private String userId;
    private String title;
    private String description;
    private Double price;
    private String payment;
    private String createdAt;
    private String updatedAt;
}
