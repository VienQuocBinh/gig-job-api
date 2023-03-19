package gigjob.service.impl;

import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.entity.Wallet;
import gigjob.model.response.WalletResponse;
import gigjob.repository.WalletRepository;
import gigjob.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final ModelMapper modelMapper;
    private final WalletRepository walletRepository;

    @Override
    public List<Wallet> ListAll() {
        return walletRepository.findAll();
    }

    @Override
    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public Wallet get(UUID id) {
        return walletRepository.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found for" + id));
    }

    @Override
    public WalletResponse getByAccountId(String accountId) {
        Wallet wallet = walletRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for " + accountId));
        return modelMapper.map(wallet, WalletResponse.class);
    }

    @Override
    public void delete(UUID id) {
        walletRepository.deleteById(id);
    }
}
