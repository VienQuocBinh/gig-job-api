package gigjob.model.response;

import gigjob.common.meta.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDetailResponse {
    private WorkerDetailResponse worker;
    private JobDetailResponse job;
    private ApplicationStatus status;
}
