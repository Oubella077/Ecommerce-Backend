package com.projet.ecommerce.Dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CartItemDTO {
    private Long Idproduct;
    private  String UserId;
    private Date createdDate;
    private Integer quantity;
}
