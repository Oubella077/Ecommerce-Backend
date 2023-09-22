package com.projet.ecommerce.Services.ServicesImp;

import com.projet.ecommerce.Repository.AppRoleRepo;
import com.projet.ecommerce.Repository.AppUserRepo;
import com.projet.ecommerce.Security.Entite.AppRole;
import com.projet.ecommerce.Security.Entite.AppUser;
import com.projet.ecommerce.Services.AccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImp implements AccountService {
    AppUserRepo appUserRepo;
    AppRoleRepo appRoleRepo;
    PasswordEncoder passwordEncoder;
    @Override
    public AppUser addAppUser(AppUser appUser) {
        AppUser appUser1=appUserRepo.findByUsername(appUser.getUsername());
        if (appUser1!=null)
            throw new RuntimeException("User Already Exist!");
        if (!appUser.getPassword().equals(appUser.getConfirmpassword()))
            throw new RuntimeException("Password Not Equals");
        AppRole appRole=appRoleRepo.findById("USER").get();
        List<AppRole> roles=new ArrayList<AppRole>();
        roles.add(appRole);
        appUser1=appUser1.builder()
                .Iduser(UUID.randomUUID().toString())
                .username(appUser.getUsername())
                .Phone(appUser.getPhone())
                .Adress(appUser.getAdress())
                .Email(appUser.getEmail())
                .Roles(roles)
                .password(passwordEncoder.encode(appUser.getPassword()))
                .build();
        return appUserRepo.save(appUser1);
    }
    @Override
    public AppRole addRole(String Role) {
        AppRole appRole=appRoleRepo.findById(Role).orElse(null);
        if (appRole!=null)
            throw new RuntimeException("User Already Exist!");
        appRole=appRole.builder()
                .Role(Role).build();
        return appRoleRepo.save(appRole);
    }
    @Override
    public AppUser addRolrtoUser(String username, String Role) {
      AppUser appUser=appUserRepo.findByUsername(username);
        AppRole appRole=appRoleRepo.findById(Role).get();
        if(appRole == null)
            throw new RuntimeException("Role Does not Exist");
        appUser.getRoles().add(appRole);
        return appUserRepo.save(appUser);
    }
    @Override
    public AppUser loadUserByUsername(String username) {
        AppUser appUser=appUserRepo.findByUsername(username);
        return appUser;
    }
}
