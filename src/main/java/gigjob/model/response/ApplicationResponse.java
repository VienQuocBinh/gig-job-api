package gigjob.model.response;

import gigjob.common.meta.ApplicationStatus;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationResponse {
    private UUID workerId;
    private JobDetailResponse job;
    private ApplicationStatus status;
}
