package gigjob.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequest {
    private Long id;
    private UUID shopId;
    private Long jobTypeId;
    private String title;
    private String description;
    private String skill;
    private String benefit;
    private Long salary; // per hour
    private Date expiredDate;
}
