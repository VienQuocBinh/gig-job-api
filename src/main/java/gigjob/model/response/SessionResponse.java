package gigjob.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import gigjob.common.meta.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionResponse {
    private Long id;
    private Shift shift;
    private Double duration; // hours
    private Date date; // working dates
    @Nullable
    private Double total;
}
