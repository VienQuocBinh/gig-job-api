package gigjob.util;

import gigjob.entity.Wallet;
import gigjob.model.response.WalletResponse;
import org.modelmapper.ModelMapper;

public class WalletMapper {

    public static WalletResponse toDto(Wallet wallet) {
        ModelMapper modelMapper = new ModelMapper();
        WalletResponse walletResponse = modelMapper.map(wallet, WalletResponse.class);
        walletResponse.setAccountId(wallet.getAccount().getId());
        return walletResponse;
    }
}
