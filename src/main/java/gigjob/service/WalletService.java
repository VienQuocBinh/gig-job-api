package gigjob.service;

import java.util.UUID;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import gigjob.entity.Wallet;
import gigjob.repository.WalletRepository;

public class WalletService {
    @Autowired
    private WalletRepository repo;

    public List<Wallet> ListAll(){
        return repo.findAll();
    }

    public void save (Wallet wallet){
        repo.save(wallet);
    }

    public Wallet get(UUID id){
        return repo.findById(id).get();
    }
    public void Delete(UUID id){
        repo.deleteById(id);
    }
}
