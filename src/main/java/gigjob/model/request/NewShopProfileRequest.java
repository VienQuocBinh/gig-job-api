package gigjob.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//request use for signing up with Google and create new shop
public class NewShopProfileRequest {
    private String name;
    private String description;
    private String accountId;
    private String email;
    private String username;
    private String password;
    private String phone;
    private AddressRequest address;
}
