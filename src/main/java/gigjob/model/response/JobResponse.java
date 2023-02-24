package gigjob.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobResponse {
    private Long id;
    private UUID shopId;
    private String jobTypeName;
    private String title;
    private String description;
    private String skill;
    private String benefit;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date createdDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date updatedDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date expiredDate;
}
