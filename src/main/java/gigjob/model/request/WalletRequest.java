package gigjob.model.request;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletRequest {
    private UUID id;
    private Double balance;
    //    private UUID accountId;
    private String accountId;
}
