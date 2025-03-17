package nl.EuniqBrillen.BrillenWebshop.controller.klant;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.recensie.RecensieService;
import nl.EuniqBrillen.BrillenWebshop.dto.BesteldeProductResponsDto;
import nl.EuniqBrillen.BrillenWebshop.dto.RecensieDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class RecensieController {
    private final RecensieService recensieService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<BesteldeProductResponsDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId) {
        BesteldeProductResponsDto besteldeProductResponsDto = recensieService.getOrderedProductsDetailsByOrderId(orderId);
        return ResponseEntity.ok(besteldeProductResponsDto);
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute RecensieDto recensieDto) throws IOException {
        RecensieDto reviewedDto = recensieService.giveReview(recensieDto);
        if (reviewedDto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewedDto);
    }
}
