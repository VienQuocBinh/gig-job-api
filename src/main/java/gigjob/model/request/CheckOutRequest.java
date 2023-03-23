package gigjob.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutRequest {
    private Long sessionId;
    private Long jobId;
    private UUID workerId;
}
