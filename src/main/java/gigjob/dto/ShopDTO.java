package gigjob.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopDTO {
    private UUID id;
    private String name;
    private String location;
    private String description;
    private String phone;
    private String email;
    private UUID accountId;
    private List<Long> jobIds;
}
