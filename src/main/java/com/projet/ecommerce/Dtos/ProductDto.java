package com.projet.ecommerce.Dtos;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
public class ProductDto {
        private Long id;
        private String productName;
        private Long price;
        private String description;
        private File file;
        private Integer Idcategorie;
    }