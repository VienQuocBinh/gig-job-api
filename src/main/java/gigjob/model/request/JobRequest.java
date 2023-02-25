package gigjob.model.request;

import lombok.Data;

import java.util.UUID;

@Data
public class JobRequest {
    private Long id;
    private UUID shopId;
    private Long jobTypeId;
    private String title;
    private String description;
    private String skill;
    private String benefit;
}
