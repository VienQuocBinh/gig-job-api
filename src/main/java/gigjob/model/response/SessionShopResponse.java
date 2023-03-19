package gigjob.model.response;

import gigjob.common.meta.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionShopResponse {
    private Long id;
    private UUID workerId;
    private WorkerDetailResponse workerDetail;
    private Shift shift;
    private Integer duration; // hours
    private Date date; // working dates

    public SessionShopResponse(Long id, UUID workerId, Shift shift, Integer duration, Date date) {
        this.id = id;
        this.workerId = workerId;
        this.shift = shift;
        this.duration = duration;
        this.date = date;
    }
}
