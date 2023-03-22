package gigjob.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import gigjob.common.meta.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionShopResponse {
    private Long id;
    private UUID workerId;
    private WorkerDetailResponse worker;
    private Shift shift;
    private Double duration; // hours
    private Date date; // working dates
    private Long salary;
    @Nullable
    private Double total;

    public SessionShopResponse(Long id, UUID workerId, Shift shift, Double duration, Date date, Long salary) {
        this.id = id;
        this.workerId = workerId;
        this.shift = shift;
        this.duration = duration;
        this.date = date;
        this.salary = salary;
    }
}
