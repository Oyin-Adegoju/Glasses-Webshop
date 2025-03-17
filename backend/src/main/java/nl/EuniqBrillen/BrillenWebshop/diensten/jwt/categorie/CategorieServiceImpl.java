package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.categorie;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.dto.CategorieDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Categorie;
import nl.EuniqBrillen.BrillenWebshop.repository.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService{
    private final CategorieRepository categorieRepository;

    public Categorie createCategory(CategorieDto categorieDto) {
        Categorie categorie = new Categorie();
        categorie.setNaam(categorieDto.getNaam());
        categorie.setBeschrijving(categorieDto.getBeschrijving());
        return categorieRepository.save(categorie);
    }
    @Override
    public List<CategorieDto>getAllCategories() {
        List<Categorie> categories = categorieRepository.findAll();
        return categories.stream().map(category -> {
            CategorieDto categoryDto = new CategorieDto();
            categoryDto.setId(category.getId());
            categoryDto.setNaam(category.getNaam());
            categoryDto.setBeschrijving(category.getBeschrijving());
            return categoryDto;
        }).collect(Collectors.toList());
    }

}
