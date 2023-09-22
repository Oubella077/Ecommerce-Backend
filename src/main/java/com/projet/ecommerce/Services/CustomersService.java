package com.projet.ecommerce.Services;

import com.projet.ecommerce.Entites.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomersService {
    Customer addcustomer(Customer customer);
}
