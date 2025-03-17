package nl.EuniqBrillen.BrillenWebshop.controller.klant;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.winkelwagen.WinkelwagenService;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import nl.EuniqBrillen.BrillenWebshop.dto.HoeveelheidVeranderingProductDto;
import nl.EuniqBrillen.BrillenWebshop.dto.WinkelwagenArtikelenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class WinkelwagenController {

    private final WinkelwagenService winkelwagenService;
    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody WinkelwagenArtikelenDto winkelwagenArtikelenDto) {
        return winkelwagenService.addProductToCart(winkelwagenArtikelenDto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<BestellingDto> getCartByUserId(@PathVariable Long userId) {
        BestellingDto orderDto = winkelwagenService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @PostMapping("/deduction")
    public ResponseEntity<BestellingDto> addMinusOnProduct(@RequestBody HoeveelheidVeranderingProductDto hoeveelheidVeranderingProductDto) {
        BestellingDto orderDto = winkelwagenService.decreaseProductQuantity(hoeveelheidVeranderingProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @PostMapping("/addition")
    public ResponseEntity<BestellingDto> increaseProductQuantity(@RequestBody HoeveelheidVeranderingProductDto hoeveelheidVeranderingProductDto) {
        BestellingDto bestellingDto = winkelwagenService.increaseProductQuantity(hoeveelheidVeranderingProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bestellingDto);
    }


}
