package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.klantbestelling;

import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingPlaatsenDto;

import java.util.List;
import java.util.UUID;

public interface KlantBestellingService {
    BestellingDto PlaceOrder(BestellingPlaatsenDto placeOrderDto);

    List<BestellingDto> getMyPlacedOrders(Long userId);

    BestellingDto searchOrderByTrackingId(UUID trackingId);

}
