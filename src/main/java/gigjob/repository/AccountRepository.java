package gigjob.repository;

import gigjob.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findAccountByUsername(String username);

    Optional<Account> findAccountByEmail(String email);

    Optional<Account> findAccountById(String id);
}
