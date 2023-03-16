package gigjob.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopRequest {
    private String name;
    private String email;
    private String username;
    private int role;
    private String password;
    private String phone;
    private String description;
    private String accountId;
    private String imageUrl;
    private AddressRequest address;
}
