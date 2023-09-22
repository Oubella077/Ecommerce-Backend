package com.projet.ecommerce.Repository;


import com.projet.ecommerce.Entites.Cart;
import com.projet.ecommerce.Security.Entite.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepo extends JpaRepository <AppRole,String> {

}

