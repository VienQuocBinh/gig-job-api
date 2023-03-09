package gigjob.model.response;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountResponse implements Serializable {
    private String id; // Get from Firebase
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date createdDate;
    private Date updatedDate;
    private boolean isLocked;
    private boolean isDisable;
    private String imageUrl;
    private String role;
}
