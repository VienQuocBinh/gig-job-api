package gigjob.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRequest {
    //    private UUID id;
    private String id; // Get from Firebase
    private String username;
    private String password;
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date createdDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date updatedDate;
    private boolean isLocked;
    private boolean isDisable;
    private String imageUrl;
    private String role;
}
