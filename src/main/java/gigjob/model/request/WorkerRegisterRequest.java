package gigjob.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRegisterRequest {
    private String accountId;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private String phone;
    private String education;
    private String username;
    private String password;
}
