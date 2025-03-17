package nl.EuniqBrillen.BrillenWebshop.controller.klant;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.wenslijst.WenslijstService;
import nl.EuniqBrillen.BrillenWebshop.dto.WenslijstDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class WensLijstController {
    private final WenslijstService wenslijstService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishlist(@RequestBody WenslijstDto wishlistDto) throws IOException {
        WenslijstDto postedWishlistDto = wenslijstService.addProductToWishlist(wishlistDto);
        if (postedWishlistDto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WenslijstDto>> getWishlistByUserId(@PathVariable Long userId) {
        List<WenslijstDto> wenslijstDtos = wenslijstService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wenslijstDtos);
    }

}
