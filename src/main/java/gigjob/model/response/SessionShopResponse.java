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
    private Shift shift;
    private Integer duration; // hours
    private Date date; // working dates
}
