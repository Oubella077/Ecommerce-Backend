package com.projet.ecommerce;

import com.projet.ecommerce.Entites.Categorie;
import com.projet.ecommerce.Entites.Customer;
import com.projet.ecommerce.Entites.Product;
import com.projet.ecommerce.Repository.CartRepo;
import com.projet.ecommerce.Repository.CategorieRepo;
import com.projet.ecommerce.Repository.CustomerRepo;
import com.projet.ecommerce.Repository.ProductRepo;
import com.projet.ecommerce.Security.Entite.AppUser;
import com.projet.ecommerce.Services.AccountService;
import com.projet.ecommerce.Services.CustomersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;
@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CategorieRepo categorieservice,
										ProductRepo productRepo,
										CustomersService customerservice, CartRepo cartsrepo, CustomerRepo customerRepo) {
	  return args -> {
		  Stream.of("Electronics", "Jewelery", "Men's clothing", "Women's Clothing","Electromanager").forEach(
				name -> {
					Categorie categorieDTO = new Categorie();
					categorieDTO.setName(name);
					categorieservice.save(categorieDTO);
				});
		Stream.of("/assets/images/11.jpg", "/assets/images/22.jpg",
				"/assets/images/33.jpg","/assets/images/44.jpg","/assets/images/55.jpg",
				"/assets/images/66.jpg","/assets/images/2.jpg","/assets/images/9.jpg").forEach(
				name -> {
					Product productDTO = new Product();
					//Product product1=new Product(null,"Product",55L,"good",name,null);
					productDTO.setProductName("Product");
					productDTO.setPrice(66L);
					productDTO.setDescription("lorem loro wre");
					productDTO.setImageUrl(name);
					productRepo.save(productDTO);
				//	Cart cartDTO=new Cart();
				//	cartDTO.setQuantity(2);
				//	cartDTO.setCreatedDate(new Date());
				//	cartsrepo.save(cartDTO);
					Customer customer=new Customer();
					customer.setPhone("99999");
					customer.setName("Megaman");
					customerRepo.save(customer);});};
	}
	@Bean
	CommandLineRunner commandLineRunnerAccountService(AccountService accountService){
		return args -> {
			accountService.addRole("ADMIN");
			accountService.addRole("USER");
			//AppUser appUser=new AppUser(null,"12345","12345","Amine","Amine123@gmail","El jadida 077","098763535",null);
	 accountService.addAppUser(new AppUser(null,"12345","12345","Amine",
				"Amine123@gmail","El jadida 077","098763535",null));
			accountService.addAppUser(new AppUser(null,"12345","12345","Oubella","Hassan123@gmail","El jadida 077","098763535",null));

        // Add roles t users
			accountService.addRolrtoUser("Amine","USER");
			accountService.addRolrtoUser("Oubella","ADMIN");
		};
	}
}