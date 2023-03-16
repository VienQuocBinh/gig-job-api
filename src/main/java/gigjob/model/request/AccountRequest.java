package gigjob.model.request;

import gigjob.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String image_url;
    private String name;
    private String description;

    private Address address;
}
