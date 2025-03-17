package nl.EuniqBrillen.BrillenWebshop.controller.admin;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.admin.productadmin.ProductAdminService;
import nl.EuniqBrillen.BrillenWebshop.dto.ProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.TweedeProductDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductAdminService productAdminService;

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@ModelAttribute TweedeProductDto tweedeProductDto) throws IOException {
        Product product = productAdminService.addProduct(tweedeProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productAdminService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto productDto = productAdminService.getProductById(productId);
        if (productDto != null) {
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    } }
