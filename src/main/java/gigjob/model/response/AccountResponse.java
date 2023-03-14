package gigjob.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import gigjob.common.meta.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Role role;
}
