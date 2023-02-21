package gigjob.util;

import gigjob.entity.Wallet;
import gigjob.model.request.WalletRequest;
import org.modelmapper.ModelMapper;

public class WalletMapper {

    public static WalletRequest toDto(Wallet wallet) {
        ModelMapper modelMapper = new ModelMapper();
        WalletRequest walletRequest = modelMapper.map(wallet, WalletRequest.class);
        walletRequest.setAccountId(wallet.getAccount().getId());
        return walletRequest;
    }
}
