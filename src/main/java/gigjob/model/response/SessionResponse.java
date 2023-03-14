package gigjob.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import gigjob.common.meta.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionResponse {
    private Long id;
    private Shift shift;
    private Integer duration; // hours
    private Date date; // working dates
    private boolean isCheckedIn;
}
