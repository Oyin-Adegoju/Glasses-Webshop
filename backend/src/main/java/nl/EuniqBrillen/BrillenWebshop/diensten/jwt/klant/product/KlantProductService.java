package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.product;

import nl.EuniqBrillen.BrillenWebshop.dto.CategorieDto;
import nl.EuniqBrillen.BrillenWebshop.dto.ProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.VolleProductDetailsDto;

import java.util.List;

public interface KlantProductService {
    List<CategorieDto> getAllCategories();

    List<ProductDto> searchProductByTitle(String title);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByCategory(Long categoryId);

    VolleProductDetailsDto getCompleteProductDetailById(Long productId);
}
