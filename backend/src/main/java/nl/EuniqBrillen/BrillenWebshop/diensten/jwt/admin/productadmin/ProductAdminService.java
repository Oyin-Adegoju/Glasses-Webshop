package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.admin.productadmin;

import nl.EuniqBrillen.BrillenWebshop.dto.ProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.TweedeProductDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductAdminService {
    Product addProduct(TweedeProductDto tweedeProductDto) throws IOException;

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;

    boolean deleteProduct(Long productId);

    List<ProductDto> searchProductByTitle(String title);
}
