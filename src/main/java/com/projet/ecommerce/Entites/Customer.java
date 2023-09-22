package com.projet.ecommerce.Entites;

import com.projet.ecommerce.Security.Entite.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @jakarta.persistence.Id
    @GeneratedValue
    private  Integer Id;
    private  String password;
    private String name;
    private String Email;
    private String Adress;
    private String Phone;
}
