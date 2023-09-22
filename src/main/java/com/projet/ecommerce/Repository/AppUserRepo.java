package com.projet.ecommerce.Repository;


import com.projet.ecommerce.Entites.Cart;
import com.projet.ecommerce.Security.Entite.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepo extends JpaRepository <AppUser,String> {
  //  @Query("SELECT c FROM Cart c WHERE c.UserId = :kw")
    //List<Cart> find(@Param("kw") Integer id);

    AppUser findByUsername(String user);
}

