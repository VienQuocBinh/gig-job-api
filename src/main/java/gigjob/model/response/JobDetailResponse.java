package gigjob.model.response;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDetailResponse implements Serializable {
    private Long id;
    private ShopResponse shop;
    private JobTypeResponse jobType;
    private String title;
    private String description;
    private String skill;
    private Long salary;
    private String benefit;
    private Date createdDate;
    private Date updatedDate;
    private Date expiredDate;
}
