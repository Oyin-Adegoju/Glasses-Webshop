package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.winkelwagen;

import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import nl.EuniqBrillen.BrillenWebshop.dto.HoeveelheidVeranderingProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.WinkelwagenArtikelenDto;
import org.springframework.http.ResponseEntity;

public interface WinkelwagenService {

    ResponseEntity<?> addProductToCart(WinkelwagenArtikelenDto winkelwagenArtikelenDto);

    BestellingDto getCartByUserId(Long userId);

    BestellingDto decreaseProductQuantity(HoeveelheidVeranderingProductDto hoeveelheidVeranderingProductDto);

    BestellingDto increaseProductQuantity(HoeveelheidVeranderingProductDto hoeveelheidVeranderingProductDto);
}
