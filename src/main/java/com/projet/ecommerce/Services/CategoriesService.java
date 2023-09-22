package com.projet.ecommerce.Services;

import com.projet.ecommerce.Entites.Categorie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface CategoriesService {
 List<Categorie> GetCategories();

    Categorie addCategorie(Categorie categorie);

    void DeleteCategorie(Integer idproduct);
}
