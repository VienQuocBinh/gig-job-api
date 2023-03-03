package gigjob.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRequest {
    private UUID id;
    private String name;
    private String description;
    private String accountId;
}
