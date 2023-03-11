package gigjob.model.response;

import gigjob.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerResponse {
    private String firstName;
    private String lastName;
    private String middleName;
    private String education;
    private Date birthday;
    private String email;
    private String phone;
    private String imgUrl;
    private List<Address> addresses;
}
