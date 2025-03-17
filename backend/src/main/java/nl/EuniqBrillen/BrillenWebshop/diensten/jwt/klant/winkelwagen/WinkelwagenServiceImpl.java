package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.winkelwagen;

import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import nl.EuniqBrillen.BrillenWebshop.dto.HoeveelheidVeranderingProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.WinkelwagenArtikelenDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Gebruiker;
import nl.EuniqBrillen.BrillenWebshop.entity.Product;
import nl.EuniqBrillen.BrillenWebshop.entity.WinkelwagenArtikelen;
import nl.EuniqBrillen.BrillenWebshop.enums.OrderStatus;
import nl.EuniqBrillen.BrillenWebshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WinkelwagenServiceImpl implements WinkelwagenService {
    @Autowired
    private BestellingRepository bestellingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WinkelmandRepository winkelmandRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public ResponseEntity<?> addProductToCart(WinkelwagenArtikelenDto winkelwagenArtikelenDto) {
        Bestelling pendingOrder = bestellingRepository.findByUserIdAndStatus(winkelwagenArtikelenDto.getUserId(), OrderStatus.Pending);
        Optional<WinkelwagenArtikelen> cartItem = winkelmandRepository.findByProductIdAndOrderIdAndUserId(winkelwagenArtikelenDto.getProductId(), pendingOrder.getId(), winkelwagenArtikelenDto.getUserId());
        if (cartItem.isPresent()) {
            WinkelwagenArtikelenDto productAlreadyExistsInCart = new WinkelwagenArtikelenDto();
            productAlreadyExistsInCart.setProductId(null);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(productAlreadyExistsInCart);
        } else {
            Product product = null;
            Optional<Product> optionalProduct = productRepository.findById(winkelwagenArtikelenDto.getProductId());
            Optional<Gebruiker> optionalUser = userRepository.findById(winkelwagenArtikelenDto.getUserId());
            Bestelling runningOrder = bestellingRepository.findByUserIdAndStatus(winkelwagenArtikelenDto.getUserId(), OrderStatus.Pending);
            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                product = optionalProduct.get();
                WinkelwagenArtikelen cart = new WinkelwagenArtikelen();
                cart.setProduct(product);
                cart.setPrijs(product.getPrijs());
                cart.setHoeveelheid(1L);
                cart.setGebruiker(optionalUser.get());
                cart.setBestelling(runningOrder);
                WinkelwagenArtikelen updatedCart = winkelmandRepository.save(cart);
                Bestelling order = bestellingRepository.findByGebruikerAndStatus(optionalUser.get(), OrderStatus.Pending);
                order.setAantal(order.getAantal() + cart.getPrijs());
                order.setTotalAmount(order.getTotalAmount() + cart.getPrijs());
                order.getWinkelwagenArtikelen().add(cart);
                bestellingRepository.save(order);
                WinkelwagenArtikelenDto producttoegvaanCartDto = new WinkelwagenArtikelenDto();
                producttoegvaanCartDto.setId(updatedCart.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(producttoegvaanCartDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gebruiker of product niet gevonden");
            }
        }
    }

    @Override
    public BestellingDto getCartByUserId(Long userId) {
        Bestelling bestelling = bestellingRepository.findByUserIdAndStatus(userId, OrderStatus.Pending);
        List<WinkelwagenArtikelenDto> cartItemsDtos = bestelling.getWinkelwagenArtikelen().stream().map(WinkelwagenArtikelen::getWinkelwagenArtikelenDto).collect(Collectors.toList());
        BestellingDto bestellingDto = new BestellingDto();
        bestellingDto.setWinkelwagenArtikelen(cartItemsDtos);
        bestellingDto.setAantal(bestelling.getAantal());
        bestellingDto.setId(bestelling.getId());
        bestellingDto.setStatus(bestelling.getStatus());


        bestellingDto.setTotaalAantal(bestelling.getTotalAmount());
        return bestellingDto;
    }

    @Override
    public BestellingDto decreaseProductQuantity(HoeveelheidVeranderingProductDto hoeveelheidVeranderingProductDto) {
        Bestelling bestelling = bestellingRepository.findByUserIdAndStatus(hoeveelheidVeranderingProductDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(hoeveelheidVeranderingProductDto.getProductId());
        Optional<WinkelwagenArtikelen> optionalCartItem = winkelmandRepository.findByProductIdAndOrderIdAndUserId(hoeveelheidVeranderingProductDto.getProductId(), bestelling.getId(), hoeveelheidVeranderingProductDto.getUserId());
        WinkelwagenArtikelen winkelwagenArtikelen = optionalCartItem.get();
        bestelling.setAantal(bestelling.getAantal() - optionalProduct.get().getPrijs());
        bestelling.setTotalAmount(bestelling.getTotalAmount() - optionalProduct.get().getPrijs());
        winkelwagenArtikelen.setHoeveelheid(optionalCartItem.get().getHoeveelheid() - 1);

        winkelmandRepository.save(winkelwagenArtikelen);
        bestellingRepository.save(bestelling);
        return bestelling.getBestellingDto();
    }

    @Override
    public BestellingDto increaseProductQuantity(HoeveelheidVeranderingProductDto hoeveelheidVeranderingProductDto) {
        Bestelling bestelling = bestellingRepository.findByUserIdAndStatus(hoeveelheidVeranderingProductDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(hoeveelheidVeranderingProductDto.getProductId());
        Optional<WinkelwagenArtikelen> optionalCartItem = winkelmandRepository.findByProductIdAndOrderIdAndUserId(hoeveelheidVeranderingProductDto.getProductId(), bestelling.getId(), hoeveelheidVeranderingProductDto.getUserId());
        WinkelwagenArtikelen winkelwagenArtikelen = optionalCartItem.get();
        Product product = optionalProduct.get();
        bestelling.setAantal(bestelling.getAantal() + optionalProduct.get().getPrijs());
        bestelling.setTotalAmount(bestelling.getTotalAmount() + optionalProduct.get().getPrijs());
        winkelwagenArtikelen.setHoeveelheid(optionalCartItem.get().getHoeveelheid() + 1);


        winkelmandRepository.save(winkelwagenArtikelen);
        bestellingRepository.save(bestelling);
        return bestelling.getBestellingDto();
    }

}
