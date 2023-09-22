package com.projet.ecommerce.Entites;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
        // the product name
        @jakarta.persistence.Id @GeneratedValue
        private Integer Id;
        private String name;
        //  currency like usd, eur ...
        private String currency;
        // our success and cancel url stripe will redirect to this links
        private String successUrl;
        private String cancelUrl;
        private long amount;
        private long quantity;
    }
