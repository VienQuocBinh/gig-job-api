package gigjob.model.request;

import gigjob.common.meta.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationApplyRequest {
    private UUID workerId;
    private Long JobId;
    private ApplicationStatus status;
}
