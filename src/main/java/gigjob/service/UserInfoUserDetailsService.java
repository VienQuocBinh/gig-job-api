package gigjob.service;

import gigjob.entity.Account;
import gigjob.model.domain.UserInfoUserDetails;
import gigjob.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementation of the {@link UserDetailsService}
 *
 * @author Vien Binh
 */
@Service
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository repository;

    /**
     * Load user by Email,
     * Username of UserDetails class is Email of UserInfoUserDetails class
     *
     * @param email the username identifying the user whose data is required.
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException if the email of the user not found
     * @author Vien Binh
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> userInfo = repository.findAccountByEmail(email);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return userInfo.map(acc -> {
                            Account account = userInfo.get();
                            String rawPassword = acc.getPassword();
                            // Not encode rawPassword again
                            if (passwordEncoder.matches(acc.getPassword(), passwordEncoder.encode(rawPassword))) {
                                account.setPassword(rawPassword);
                            }
                            // Set email, password, authorities to UserDetails through UserInfoUserDetails class
                            account.setPassword(passwordEncoder.encode(rawPassword));
                            return new UserInfoUserDetails(account);
                        }
                )
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
    }
}
