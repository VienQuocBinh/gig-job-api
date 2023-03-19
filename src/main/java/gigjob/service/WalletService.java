package gigjob.service;

import gigjob.entity.Wallet;
import gigjob.model.response.WalletResponse;

import java.util.List;
import java.util.UUID;

public interface WalletService {
    List<Wallet> ListAll();

    void save(Wallet wallet);

    Wallet get(UUID id);

    WalletResponse getByAccountId(String accountId);

    void delete(UUID id);
}
