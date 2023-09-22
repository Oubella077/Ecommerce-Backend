package com.projet.ecommerce.Services.ServicesImp;

import com.projet.ecommerce.Entites.Customer;
import com.projet.ecommerce.Repository.CustomerRepo;
import com.projet.ecommerce.Services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomersServiceImp implements CustomersService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerRepo customerRepo;
    @Override
    public Customer addcustomer(Customer customer) {
        Customer customer1=new Customer();
        customer1.setEmail(customer.getEmail());
        customer1.setName(customer.getName());
        customer1.setAdress(customer.getAdress());
        passwordEncoder.encode(customer.getPassword());
        customer1.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepo.save(customer1);
    }
}
