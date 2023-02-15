package gigjob.service;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.cloud.Role;
import com.google.firebase.auth.internal.DownloadAccountResponse.User;

import antlr.collections.List;


public class MyUserDetails implements UserDetails {

    private User user;
    
    public MyUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority > getAuthorities(){
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>()
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    @Override
    public String getPassword(){
        return user.getPassword();
    }
    @Override
    public String getUsername(){
        
        return user.getUsername();
    }
    @Override
    public boolean isAccountNonExpired(){
        return false;
    }
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isEnabled(){
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

//aaa
}