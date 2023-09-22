package com.projet.ecommerce.WebControllers;
import com.projet.ecommerce.Dtos.ProductDto;
import com.projet.ecommerce.Entites.Product;
import com.projet.ecommerce.Services.ProductsService;
import com.projet.ecommerce.exception.ExceptionDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductApi {
    ProductsService productsService;
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Product> AddProduct(@RequestBody ProductDto product) throws ExceptionDetails, IOException {
        return new ResponseEntity<>(productsService.Addproduct(product, product.getFile()), HttpStatus.OK);}
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    @GetMapping("/liste")
    public ResponseEntity<List<Product>> Getproducts() {
        return new ResponseEntity<>(productsService.GetAllproduct(), HttpStatus.OK);}
    @DeleteMapping("/delete/{idproduct}")
    public void Deleteproducts(@PathVariable Long idproduct) {
        this.productsService.Deleteproduct(idproduct);
    }
    @PutMapping("/update/{idProduct}")
    public ResponseEntity<Product> Updateproducts(@PathVariable Long idProduct, @RequestBody ProductDto productDTO) throws ExceptionDetails {
        return new ResponseEntity<>(productsService.Updateproduct(idProduct, productDTO), HttpStatus.OK);
    }
}
