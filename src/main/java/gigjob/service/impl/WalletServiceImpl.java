package gigjob.service.impl;

import gigjob.common.exception.model.InternalServerErrorException;
import gigjob.common.exception.model.ResourceNotFoundException;
import gigjob.entity.Wallet;
import gigjob.model.request.WalletRequest;
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
    public WalletResponse create(WalletRequest walletRequest) {
        try {
            if (getByAccountId(walletRequest.getAccountId()) != null) {
                throw new InternalServerErrorException("Wallet of account id " + walletRequest.getAccountId() + " is already exist");
            }
        } catch (ResourceNotFoundException e) {
            // If account does not have a wallet -> create a new one with balance = 0
            walletRequest.setBalance(0D);
            Wallet wallet = walletRepository.save(modelMapper.map(walletRequest, Wallet.class));
            return modelMapper.map(wallet, WalletResponse.class);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        return null;
    }

    @Override
    public Wallet get(UUID id) {
        return walletRepository.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found for" + id));
    }

    @Override
    public WalletResponse getByAccountId(String accountId) {
        Wallet wallet = walletRepository.findByAccountId(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for account id: " + accountId));
        return modelMapper.map(wallet, WalletResponse.class);
    }

    @Override
    public void delete(UUID id) {
        walletRepository.deleteById(id);
    }
}
