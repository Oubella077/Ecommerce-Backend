package com.projet.ecommerce.Security;

import com.projet.ecommerce.Security.Entite.AppUser;
import com.projet.ecommerce.Services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailserviceImp implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=accountService.loadUserByUsername(username);
        if(appUser == null)
            throw  new UsernameNotFoundException(String.format("User with %s",username));
        String[] roles=appUser.getRoles().stream().map(p->p.getRole()).toArray(String[]::new);
        UserDetails userDetails= User
                .withUsername(appUser.getUsername())
                .password(appUser.getPassword())
                .authorities(roles)

                .build();
        return userDetails;
    }
}
