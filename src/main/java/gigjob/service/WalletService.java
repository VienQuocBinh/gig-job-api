package gigjob.service;

import gigjob.entity.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletService {
    List<Wallet> ListAll();

    void save(Wallet wallet);

    Wallet get(UUID id);

    void delete(UUID id);
}
