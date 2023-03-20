package gigjob.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegisterRequest {
    private String id; // From firebase
    private String email; // From firebase
    private String username;
//    private String password;
}
