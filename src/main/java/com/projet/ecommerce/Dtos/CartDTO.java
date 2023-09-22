package com.projet.ecommerce.Dtos;
import com.projet.ecommerce.Entites.Product;
import lombok.Data;
@Data
public class CartDTO {
        private Integer Id;
        private Product product;
        private Integer quantity;
    }

