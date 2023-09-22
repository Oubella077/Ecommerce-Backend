package com.projet.ecommerce.Security.Entite;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
    @jakarta.persistence.Id
    private  String Iduser;
    private  String password;
    private  String confirmpassword;
    private String username;
    private String Email;
    private String Adress;
    private String Phone;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> Roles;

}
