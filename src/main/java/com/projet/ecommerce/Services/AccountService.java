package com.projet.ecommerce.Services;
import com.projet.ecommerce.Security.Entite.AppRole;
import com.projet.ecommerce.Security.Entite.AppUser;
import org.springframework.stereotype.Service;
@Service
public interface AccountService {
    AppUser addAppUser(AppUser appUser);
    AppRole addRole( String Role);
    AppUser addRolrtoUser(String username, String appRole);
    AppUser loadUserByUsername(String username);



}
