package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.recensie;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.dto.BesteldeProductGegevens;
import nl.EuniqBrillen.BrillenWebshop.dto.BesteldeProductResponsDto;
import nl.EuniqBrillen.BrillenWebshop.dto.RecensieDto;
import nl.EuniqBrillen.BrillenWebshop.entity.*;
import nl.EuniqBrillen.BrillenWebshop.repository.*;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecensieServiceImpl implements RecensieService{

    private final WinkelmandRepository winkelmandRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final BestellingRepository bestellingRepository;

    private final RecensieRepository recensieRepository;

    @Override
    public BesteldeProductResponsDto getOrderedProductsDetailsByOrderId(Long orderId) {
        List<WinkelwagenArtikelen> cartItems = winkelmandRepository.findByOrderId(orderId);
        Optional<Bestelling> optionalOrder = bestellingRepository.findById(orderId);
        BesteldeProductResponsDto  besteldeProductResponsDto= new BesteldeProductResponsDto();
        if (optionalOrder.isPresent()) {
            Long orderAmount = optionalOrder.get().getAantal();
            besteldeProductResponsDto.setAantalBestelling(orderAmount);
        }
        if (!cartItems.isEmpty()) {
            List<BesteldeProductGegevens> orderedProductDetailsList = new ArrayList<>();
            for (WinkelwagenArtikelen cartItem : cartItems) {
                BesteldeProductGegevens productDetails = new BesteldeProductGegevens();
                productDetails.setId(cartItem.getProduct().getId());
                productDetails.setNaam(cartItem.getProduct().getNaam());
                productDetails.setProductPrijs(cartItem.getPrijs());
                productDetails.setHoeveelheid(cartItem.getHoeveelheid());
                productDetails.setReturnedImg(cartItem.getProduct().getImg());
                orderedProductDetailsList.add(productDetails);
            }
            besteldeProductResponsDto.setBesteldeProductGegevensList(orderedProductDetailsList);
        }
        return besteldeProductResponsDto;
    }

    @Override
    public RecensieDto giveReview(RecensieDto reviewDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
        Optional<Gebruiker> optionalUser = userRepository.findById(reviewDto.getUserId());
        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            Recensie review = new Recensie();
            review.setBeordeling(reviewDto.getBeordeling());
            review.setDescription(reviewDto.getBeschrijving());
            review.setImg(reviewDto.getImg().getBytes());
            review.setGebruiker(optionalUser.get());
            review.setProduct(optionalProduct.get());
            Recensie reviewed = recensieRepository.save(review);
            RecensieDto reviewedDto = new RecensieDto();
            reviewedDto.setId(reviewed.getId());
            return reviewedDto;
        }
        return null;
    }
}
