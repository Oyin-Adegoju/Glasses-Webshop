package nl.EuniqBrillen.BrillenWebshop.controller.admin;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.fgv.FGVService;
import nl.EuniqBrillen.BrillenWebshop.dto.FGVDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class FGVController {

    private final FGVService fgvService;

    @PostMapping("/faq/{productId}")
    public ResponseEntity<FGVDto> postFAQ(@PathVariable Long productId, @RequestBody FGVDto fgvDto) {
        FGVDto createdFaqDto = fgvService.postFGV(productId, fgvDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFaqDto);
    }

}
