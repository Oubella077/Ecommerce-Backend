package com.projet.ecommerce.Entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @JoinColumn(name="product_id")
    @ManyToOne
    private Product product;
    private  String UserId;
    private Date createdDate;
    private Integer quantity;
}

