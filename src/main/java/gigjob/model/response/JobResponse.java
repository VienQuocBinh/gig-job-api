package gigjob.model.response;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobResponse implements Serializable {
    private Long id;
    private UUID shopId;
    private Long jobTypeId;
    private String title;
    private String description;
    private String skill;
    private String benefit;
    private Date createdDate;
    private Date updatedDate;
    private Date expiredDate;
}
