package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.categorie;

import nl.EuniqBrillen.BrillenWebshop.dto.CategorieDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie createCategory(CategorieDto categorieDto);

    List<CategorieDto>getAllCategories();
}
