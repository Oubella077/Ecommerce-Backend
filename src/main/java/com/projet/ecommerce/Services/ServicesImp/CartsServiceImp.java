package com.projet.ecommerce.Services.ServicesImp;

import com.projet.ecommerce.Dtos.CartDTO;
import com.projet.ecommerce.Dtos.CartItemDTO;
import com.projet.ecommerce.Entites.Cart;
import com.projet.ecommerce.Mapper.Mappers;
import com.projet.ecommerce.Repository.AppUserRepo;
import com.projet.ecommerce.Repository.CartRepo;
import com.projet.ecommerce.Repository.ProductRepo;
import com.projet.ecommerce.Security.Entite.AppUser;
import com.projet.ecommerce.Services.AccountService;
import com.projet.ecommerce.Services.CartsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartsServiceImp implements CartsService {
    ProductRepo productRepo;
    CartRepo cartsRepo;
    AppUserRepo appUserRepo;
    Mappers mappers;
    AccountService accountService;
    @Override
    public void delete(Integer id,String user) {

        Cart cart= cartsRepo.findById(id).get();
        AppUser appUser=appUserRepo.findByUsername(user);
        if (cart.getUserId().equals(appUser.getIduser()))
              cartsRepo.deleteById(id);
        //cartRepo.delete(cart);
    }
    @Override
    public List<CartDTO> getCartproduct(String userId) {
        //AppUser user=accountService.loadUserByUsername(userId);
       AppUser appUser=appUserRepo.findByUsername(userId);
        List<Cart> carts=cartsRepo.find(appUser.getIduser());
       List<CartDTO> cartDTOS=carts.stream().map(c ->mappers.fromcart(c)).collect(Collectors.toList());
        return cartDTOS ;}
    @Override
    public Cart addtocart(CartItemDTO cartDTO) {
        Cart cart=new Cart();
        cart.setQuantity(cartDTO.getQuantity());
        cart.setUserId(cartDTO.getUserId());
        cart.setCreatedDate(cartDTO.getCreatedDate());
        cart.setProduct(productRepo.findById(cartDTO.getIdproduct()).get());
        return cartsRepo.save(cart);
    }
}
