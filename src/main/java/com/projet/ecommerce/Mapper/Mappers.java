package com.projet.ecommerce.Mapper;

import com.projet.ecommerce.Dtos.CartDTO;
import com.projet.ecommerce.Dtos.CartItemDTO;
import com.projet.ecommerce.Dtos.ProductDto;
import com.projet.ecommerce.Entites.Cart;
import com.projet.ecommerce.Entites.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Mappers {
    public static CartDTO fromcart(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(cart, cartDTO);
        cartDTO.setId(cart.getId());
        //productDTO.setId(product.getId());
        return cartDTO;
    }
    public static Product fromproduct(ProductDto productDto) {
        Product product1 = new Product();
        BeanUtils.copyProperties(productDto, product1);
        //productDTO.setId(product.getId());
        return product1;
    }
}
