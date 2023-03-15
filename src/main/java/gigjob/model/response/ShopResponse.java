package gigjob.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopResponse implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private String accountId;
    private AccountResponse account;
    private List<AddressResponse> addresses;
}
