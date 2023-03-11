package gigjob.service.impl;

import gigjob.entity.Wallet;
import gigjob.repository.WalletRepository;
import gigjob.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository repo;

    @Override
    public List<Wallet> ListAll() {
        return repo.findAll();
    }

    @Override
    public void save(Wallet wallet) {
        repo.save(wallet);
    }

    @Override
    public Wallet get(UUID id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found for" + id));
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
