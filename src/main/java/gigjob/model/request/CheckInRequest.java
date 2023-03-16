package gigjob.model.request;

import gigjob.common.meta.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInRequest {
    private UUID workerId;
    private Long jobId;
    private int duration;
    private Shift shift;
}
