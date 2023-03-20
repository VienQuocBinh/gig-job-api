package gigjob.service;

import gigjob.entity.Wallet;
import gigjob.model.request.WalletRequest;
import gigjob.model.response.WalletResponse;

import java.util.List;
import java.util.UUID;

public interface WalletService {
    List<Wallet> ListAll();

    WalletResponse create(WalletRequest walletRequest);

    Wallet get(UUID id);

    WalletResponse getByAccountId(String accountId);

    void delete(UUID id);
}
