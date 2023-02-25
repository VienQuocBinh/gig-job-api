package gigjob.model.response;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletResponse {
    private UUID id;
    private Double balance;
    private String accountId;
}
