package gigjob.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @Schema(name = "email", example = "phamhuy09042002@gmail.com")
    private String email;
    @Schema(name = "password", example = "3")
    private String password;
}
