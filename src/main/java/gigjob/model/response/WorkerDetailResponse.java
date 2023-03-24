package gigjob.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDetailResponse implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String education;
    // account info
    private String email;
    private String phone;
    private String username;
    private String password;
    private String imageUrl;
    //working history
    private List<HistoryResponse> history;
}
