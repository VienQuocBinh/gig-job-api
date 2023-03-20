package gigjob.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerUpdateRequest {
    private UUID id;
    private String accountId;
    private String firstName;
    private String lastName;
    private String middleName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String phone;
    private String education;
    private String username;
    private String password;
}
