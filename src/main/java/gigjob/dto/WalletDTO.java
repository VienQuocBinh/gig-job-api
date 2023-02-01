package gigjob.dto;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WalletDTO {
    private UUID id;
    private Double balance;
    private UUID accountId;
}
