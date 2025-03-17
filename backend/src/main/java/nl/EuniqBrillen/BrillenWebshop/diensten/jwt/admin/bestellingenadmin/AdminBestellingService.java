package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.admin.bestellingenadmin;

import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;

import java.util.List;

public interface AdminBestellingService {
    List<BestellingDto> getAllPlacedOrders();
    BestellingDto changeOrderStatus(Long orderId, String status);

}
