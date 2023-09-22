package com.projet.ecommerce.Services.ServicesImp;

import com.projet.ecommerce.Dtos.ProductDto;
import com.projet.ecommerce.Entites.Categorie;
import com.projet.ecommerce.Entites.Product;
import com.projet.ecommerce.Mapper.Mappers;
import com.projet.ecommerce.Repository.CategorieRepo;
import com.projet.ecommerce.Repository.ProductRepo;
import com.projet.ecommerce.Services.ProductsService;
import com.projet.ecommerce.exception.BadRequestException;
import com.projet.ecommerce.exception.ExceptionDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductsServiceImp implements ProductsService {
    ProductRepo productRepo;
    @Autowired
    CategorieRepo categorieRepo;
    @Override
    public Product Addproduct(ProductDto product, File file) throws ExceptionDetails {
        try  { Product product1=new Product();
             product1.setProductName(product.getProductName());
             product1.setDescription(product.getDescription());
             product1.setPrice(product.getPrice());
             Categorie categorie=categorieRepo.findById(product.getIdcategorie()).get();
             product1.setCategorie(categorie);
            String fileName =file.getAbsolutePath().replace("C:\\fakepath\\" ,"/assets/images/");
            product1.setImageUrl(fileName);

        return productRepo.save(product1);
       }catch (Exception e){
        throw new ExceptionDetails("Error in adding product","/url/");
    }
    }
    @Override
    public List<Product> GetAllproduct() {
        try{
            return productRepo.findAll();
        }catch (Exception e){
             throw new BadRequestException("List not Found");}}
    @Override
    public void Deleteproduct(Long idproduct) {
     productRepo.deleteById(idproduct);
    }
    @Override
    public Product Updateproduct(Long idProduct, ProductDto product) throws ExceptionDetails {
        Product product1=productRepo.findById(idProduct).get();
        BeanUtils.copyProperties(product, product1);
      //  product1.setId(product.getId());
      //  product1.setProductName(product.getProductName());
        //product1.setImageUrl(product.getImageUrl());
        //product1.setPrice(product.getPrice());
        //product1.setDescription(product.getDescription());
     try {  Categorie categorie=categorieRepo.findById(product.getIdcategorie()).get();
        product1.setCategorie(categorie);
        return productRepo.save(product1);
     }catch (Exception e){
         throw new BadRequestException("Erorr updating the product categorie");
     }
     }
    }