package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.admin.productadmin;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.dto.ProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.TweedeProductDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Categorie;
import nl.EuniqBrillen.BrillenWebshop.entity.Product;
import nl.EuniqBrillen.BrillenWebshop.repository.CategorieRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductAdminServiceImpl implements ProductAdminService{

    private final ProductRepository productRepository;

    private final CategorieRepository categorieRepository;

    @Override
    public Product addProduct(TweedeProductDto tweedeProductDto) throws IOException {
        Product product = new Product();
        product.setNaam(tweedeProductDto.getNaam());
        product.setPrijs(tweedeProductDto.getPrijs());
        product.setBeschrijving(tweedeProductDto.getBeschrijving());
        product.setImg(tweedeProductDto.getImg().getBytes());
        Categorie categorie = categorieRepository.findById(Long.parseLong(tweedeProductDto.getCategorieId())).orElseThrow(() -> new RuntimeException("Categorie niet gevonden"));
        product.setCategorie(categorie);
        return productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setNaam(product.getNaam());
            productDto.setBeschrijving(product.getBeschrijving());
            productDto.setBeschrijving(product.getBeschrijving());
            productDto.setCategorieId(product.getCategorie().getId());
            productDto.setCategorieNaam(product.getCategorie().getNaam());
            productDto.setReturnedImg(product.getImg());
            return productDto;
        }).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getProductDto();
        } else {
            return null;
        }
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Categorie> category = categorieRepository.findById(productDto.getCategorieId());
        if (optionalProduct.isPresent() && category.isPresent()) {
            Product product = optionalProduct.get();
            product.setNaam(productDto.getNaam());
            product.setPrijs(productDto.getPrijs());
            product.setBeschrijving(productDto.getBeschrijving());
            if (productDto.getImg() != null) {
                product.setImg(productDto.getImg().getBytes());
            }
            product.setCategorie(category.get());
            return productRepository.save(product).getProductDto();
        } else {
            return null;
        }
    }


    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ProductDto> searchProductByTitle(String title) {
        List<Product> products = productRepository.findAllByNameContaining(title);
        return products.stream().map(product -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setNaam(product.getNaam());
            productDto.setBeschrijving(product.getBeschrijving());
            productDto.setPrijs(product.getPrijs());
            productDto.setCategorieId(product.getCategorie().getId());
            productDto.setCategorieNaam(product.getCategorie().getNaam());
            productDto.setReturnedImg(product.getImg());
            return productDto;
        }).collect(Collectors.toList());
    }
}
