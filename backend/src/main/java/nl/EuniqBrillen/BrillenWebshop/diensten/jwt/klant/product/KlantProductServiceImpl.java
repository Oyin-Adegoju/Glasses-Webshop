package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.product;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.dto.CategorieDto;
import nl.EuniqBrillen.BrillenWebshop.dto.ProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.VolleProductDetailsDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Categorie;
import nl.EuniqBrillen.BrillenWebshop.entity.FGV;
import nl.EuniqBrillen.BrillenWebshop.entity.Product;
import nl.EuniqBrillen.BrillenWebshop.entity.Recensie;
import nl.EuniqBrillen.BrillenWebshop.repository.CategorieRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.FGVRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.ProductRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.RecensieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KlantProductServiceImpl implements KlantProductService{

    private final CategorieRepository categorieRepository;

    private final ProductRepository productRepository;

    private final FGVRepository fgvRepository;

    private final RecensieRepository recensieRepository;


    @Override
    public List<CategorieDto> getAllCategories() {
        return categorieRepository.findAll().stream().map(Categorie::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchProductByTitle(String title) {
        return productRepository.findAllByNameContaining(title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategorieId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public VolleProductDetailsDto getCompleteProductDetailById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        List<FGV> faqList = fgvRepository.findAllByProductId(productId);
        List<Recensie> reviewsList = recensieRepository.findAllByProductId(productId);
        if (optionalProduct.isPresent()) {
            VolleProductDetailsDto volleProductDetailsDto = new VolleProductDetailsDto();
            volleProductDetailsDto.setProductDto(optionalProduct.get().getProductDto());
            volleProductDetailsDto.setFgvDtoList(faqList.stream().map(FGV::getFGVDto).collect(Collectors.toList()));
            volleProductDetailsDto.setRecensieDtoList(reviewsList.stream().map(Recensie::getRecensieDto).collect(Collectors.toList()));
            return volleProductDetailsDto;
        }
        return null;
    }

}
