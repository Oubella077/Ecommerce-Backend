package com.projet.ecommerce.Services;

import com.projet.ecommerce.Dtos.CartDTO;
import com.projet.ecommerce.Dtos.CartItemDTO;
import com.projet.ecommerce.Entites.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartsService {
    void delete(Integer id,String iduser);

    List<CartDTO> getCartproduct(String id);

    Cart addtocart(CartItemDTO cartDTO);
}
