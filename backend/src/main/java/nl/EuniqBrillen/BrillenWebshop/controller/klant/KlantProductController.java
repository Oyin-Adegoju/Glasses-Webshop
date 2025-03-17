package nl.EuniqBrillen.BrillenWebshop.controller.klant;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.product.KlantProductService;
import nl.EuniqBrillen.BrillenWebshop.dto.CategorieDto;
import nl.EuniqBrillen.BrillenWebshop.dto.ProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.VolleProductDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class KlantProductController {
    private final KlantProductService klantProductService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategorieDto>> getAllCategories() {
        List<CategorieDto> categoryDtos = klantProductService.getAllCategories();
        return ResponseEntity.ok(categoryDtos);
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<List<ProductDto>> searchProductByTitle(@PathVariable("title") String title) {
        List<ProductDto> productDtos = klantProductService.searchProductByTitle(title);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = klantProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/products/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId) {
        List<ProductDto> productDtos = klantProductService.getProductsByCategory(categoryId);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<VolleProductDetailsDto> getProductDetailById(@PathVariable Long productId) {
        VolleProductDetailsDto completeProductDetailDto = klantProductService.getCompleteProductDetailById(productId);
        if (completeProductDetailDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(completeProductDetailDto);
    }

}
