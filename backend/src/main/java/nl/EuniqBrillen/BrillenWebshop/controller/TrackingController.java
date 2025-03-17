package nl.EuniqBrillen.BrillenWebshop.controller;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.klantbestelling.KlantBestellingService;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TrackingController {
    private final KlantBestellingService klantBestellingServicee;

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<BestellingDto> searchOrderByTrackingId(@PathVariable UUID trackingId) {
        BestellingDto orderDto = klantBestellingServicee.searchOrderByTrackingId(trackingId);
        if (orderDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDto);
    }
}
