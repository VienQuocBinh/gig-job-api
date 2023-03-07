package gigjob.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopResponse {
    private UUID id;
    private String name;
    private String description;
    private String accountId;
    private List<Long> jobIds;
}
