package nl.EuniqBrillen.BrillenWebshop.controller.klant;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.klantbestelling.KlantBestellingService;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingPlaatsenDto;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class KlantBestellingController {

    private final KlantBestellingService klantBestellingService;

    @PostMapping("/placeOrder")
    public ResponseEntity<BestellingDto> placeOrder(@RequestBody BestellingPlaatsenDto bestellingPlaatsenDto) {
        BestellingDto bestellingDto = klantBestellingService.PlaceOrder(bestellingPlaatsenDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bestellingDto);
    }


    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<BestellingDto>> getMyPlacedOrders(@PathVariable Long userId) {
        List<BestellingDto> orderDtos = klantBestellingService.getMyPlacedOrders(userId);
        return ResponseEntity.ok(orderDtos);
    }


}
