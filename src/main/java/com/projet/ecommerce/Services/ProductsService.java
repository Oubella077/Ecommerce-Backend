package com.projet.ecommerce.Services;

import com.projet.ecommerce.Dtos.ProductDto;
import com.projet.ecommerce.Entites.Product;
import com.projet.ecommerce.exception.ExceptionDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public interface ProductsService {
    Product Addproduct(ProductDto product, File file) throws ExceptionDetails;
    List<Product> GetAllproduct();
    void Deleteproduct(Long idproduct);
    Product Updateproduct(Long idProduct, ProductDto productDTO) throws ExceptionDetails;
}
