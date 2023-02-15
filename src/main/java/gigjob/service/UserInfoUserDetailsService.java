package gigjob.service;

import gigjob.config.UserInfoUserDetails;
import gigjob.entity.Account;
import gigjob.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> userInfo = repository.findAccountByUsername(username);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return userInfo.map(acc -> {
                            Account account = userInfo.get();
                            account.setPassword(passwordEncoder.encode(acc.getPassword()));
                            return new UserInfoUserDetails(account);
                        }
                )
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
