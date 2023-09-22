package com.projet.ecommerce.Repository;

import com.projet.ecommerce.Entites.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepo extends JpaRepository<Categorie,Integer> {

}
