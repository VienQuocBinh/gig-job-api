package gigjob.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @Schema(name = "username", example = "admin")
    private String username;
    @Schema(name = "password", example = "1")
    private String password;
}
