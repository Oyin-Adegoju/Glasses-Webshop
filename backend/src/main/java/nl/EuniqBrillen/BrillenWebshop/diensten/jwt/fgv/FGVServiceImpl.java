package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.fgv;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.dto.FGVDto;
import nl.EuniqBrillen.BrillenWebshop.entity.FGV;
import nl.EuniqBrillen.BrillenWebshop.entity.Product;
import nl.EuniqBrillen.BrillenWebshop.repository.FGVRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class FGVServiceImpl implements FGVService {
    private final FGVRepository fgvRepository;

    private final ProductRepository productRepository;

    @Override
    public FGVDto postFGV(Long productId, FGVDto faqDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            FGV fgv = new FGV();
            fgv.setVraag(faqDto.getVraag());
            fgv.setAntwoord(faqDto.getAntwoord());
            fgv.setProduct(optionalProduct.get());
            FGV createdFgv = fgvRepository.save(fgv);
            FGVDto createdFaqDto = new FGVDto();
            createdFaqDto.setId(createdFgv.getId());
            return createdFaqDto;
        }
        return null;
    }

}
