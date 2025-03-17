package nl.EuniqBrillen.BrillenWebshop.controller.admin;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.categorie.CategorieService;
import nl.EuniqBrillen.BrillenWebshop.dto.CategorieDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Categorie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class CategorieController {
    private final CategorieService categorieService;

    @PostMapping("/category")
    public ResponseEntity<Categorie> createCategory(@RequestBody CategorieDto categorieDto) {
        Categorie categorie = categorieService.createCategory(categorieDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categorie);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategorieDto>> getAllCategories() {
        List<CategorieDto> categoryDtos = categorieService.getAllCategories();
        return ResponseEntity.ok(categoryDtos);
    }
}
