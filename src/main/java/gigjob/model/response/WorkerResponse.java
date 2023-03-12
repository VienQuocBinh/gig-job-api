package gigjob.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponse implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private String education;
}
