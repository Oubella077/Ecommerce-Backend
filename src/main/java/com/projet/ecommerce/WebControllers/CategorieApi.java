package com.projet.ecommerce.WebControllers;

import com.projet.ecommerce.Entites.Categorie;
import com.projet.ecommerce.Services.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Categories")
@AllArgsConstructor
@CrossOrigin("*")
public class CategorieApi {
CategoriesService categoriesService;
@PreAuthorize("hasAuthority('SCOPE_USER')")
@GetMapping("/Liste")
    public ResponseEntity<List<Categorie>> GetAllCategories(){
        return new ResponseEntity<>(categoriesService.GetCategories(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Categorie> AddCategories(@RequestBody Categorie categorie){
        return new ResponseEntity<>(categoriesService.addCategorie(categorie), HttpStatus.OK);}
    @DeleteMapping("/delete/{idproduct}")
    public void Deleteproducts(@PathVariable Integer idproduct)
    {this.categoriesService.DeleteCategorie(idproduct);}
}
