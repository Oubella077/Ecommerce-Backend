package com.projet.ecommerce.Repository;


import com.projet.ecommerce.Entites.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepo extends JpaRepository <Cart,Integer> {
    @Query("SELECT c FROM Cart c WHERE c.UserId LIKE %:kw% ")
    List<Cart> find(@Param("kw") String userId);
}

