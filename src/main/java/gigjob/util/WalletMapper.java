package gigjob.util;

import gigjob.dto.WalletDTO;
import gigjob.entity.Wallet;
import org.modelmapper.ModelMapper;

public class WalletMapper {

    public static WalletDTO toDto(Wallet wallet) {
        ModelMapper modelMapper = new ModelMapper();
        WalletDTO walletDTO = modelMapper.map(wallet, WalletDTO.class);
        walletDTO.setAccountId(wallet.getAccount().getId());
        return walletDTO;
    }
}
