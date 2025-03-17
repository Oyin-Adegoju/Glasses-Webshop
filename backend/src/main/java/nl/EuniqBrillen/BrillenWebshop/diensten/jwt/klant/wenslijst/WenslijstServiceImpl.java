package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.wenslijst;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.dto.WenslijstDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Gebruiker;
import nl.EuniqBrillen.BrillenWebshop.entity.Product;
import nl.EuniqBrillen.BrillenWebshop.entity.Wenslijst;
import nl.EuniqBrillen.BrillenWebshop.repository.ProductRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.UserRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.WensLijstRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WenslijstServiceImpl implements WenslijstService{
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final WensLijstRepository wensLijstRepository;

    @Override
    public WenslijstDto addProductToWishlist(WenslijstDto wishlistDto) {
        Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
        Optional<Gebruiker> optionalUser = userRepository.findById(wishlistDto.getUserId());
        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            Wenslijst wishlist = new Wenslijst();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setGebruiker(optionalUser.get());
            Wenslijst createdWishlist = wensLijstRepository.save(wishlist);
            WenslijstDto createdWishlistDto = new WenslijstDto();
            createdWishlistDto.setId(createdWishlist.getId());
            return createdWishlistDto;
        }
        return null;
    }

    @Override
    public List<WenslijstDto> getWishlistByUserId(Long userId) {
        return wensLijstRepository.findAllByUser_Id(userId).stream().map(Wenslijst::getWenslijstDto).collect(Collectors.toList());
    }
}
