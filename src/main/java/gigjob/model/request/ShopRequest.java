package gigjob.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRequest {
    private String name;
    private String description;
    private String accountId;
    private List<Long> jobIds;
}
