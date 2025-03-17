package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.wenslijst;

import nl.EuniqBrillen.BrillenWebshop.dto.WenslijstDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Wenslijst;

import java.util.List;

public interface WenslijstService {

    WenslijstDto addProductToWishlist(WenslijstDto wenslijstDto);

    List<WenslijstDto> getWishlistByUserId(Long userId);
}
