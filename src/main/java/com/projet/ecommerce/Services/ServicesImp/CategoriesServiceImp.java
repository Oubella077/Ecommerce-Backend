package com.projet.ecommerce.Services.ServicesImp;

import com.projet.ecommerce.Entites.Categorie;
import com.projet.ecommerce.Repository.CategorieRepo;
import com.projet.ecommerce.Services.CategoriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriesServiceImp implements CategoriesService {

    CategorieRepo categoriesRepo;
    @Override
    public List<Categorie> GetCategories() {
        return categoriesRepo.findAll();
    }
    @Override
    public Categorie addCategorie(Categorie categorie) {
      Categorie categorie1=new Categorie();
        categorie1.setName(categorie.getName());
        return categoriesRepo.save(categorie1);

    }
    @Override
    public void DeleteCategorie(Integer idproduct) {
       categoriesRepo.deleteById(idproduct);
    }
}
