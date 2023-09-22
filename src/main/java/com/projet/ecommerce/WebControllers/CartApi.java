package com.projet.ecommerce.WebControllers;
import com.projet.ecommerce.Dtos.CartDTO;
import com.projet.ecommerce.Dtos.CartItemDTO;
import com.projet.ecommerce.Entites.Cart;
import com.projet.ecommerce.Security.Entite.AppUser;
import com.projet.ecommerce.Services.CartsService;
import com.projet.ecommerce.Services.CustomersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/cart")
@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CartApi {
    CartsService cartservice;
    CustomersService customerservice;
    @PostMapping("/add")
    public ResponseEntity<Cart> Addtocart(@RequestBody CartItemDTO cartDTO){
        return new  ResponseEntity<>(cartservice.addtocart(cartDTO), HttpStatus.OK);}
    // public ResponseEntity<Product> Addtocart(@RequestBody CartDTO cartDTO){
    //   return new  ResponseEntity<>(cartservice.posttocart(cartDTO), HttpStatus.OK);
    // Get liste of cart product for a customer
    @GetMapping("/Liste")
    public ResponseEntity<List<CartDTO>> GetListe(HttpServletRequest request){
      //  JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String user=request.getUserPrincipal().getName();
        return new ResponseEntity<>(cartservice.getCartproduct(user),HttpStatus.OK);}
    @DeleteMapping("/delete/{id}")
    public void  delete(@PathVariable Integer id,HttpServletRequest request){
      //  String ident=token.getToken().getSubject();
        String user=request.getUserPrincipal().getName();
        this.cartservice.delete(id,user);
    }
}
