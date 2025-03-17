package nl.EuniqBrillen.BrillenWebshop.controller.admin;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.diensten.jwt.admin.bestellingenadmin.AdminBestellingService;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminBestellingController {

    private final AdminBestellingService adminOrderService;

    @GetMapping("/placedOrders")
    public ResponseEntity<List<BestellingDto>> getAllPlacedOrders() {
        List<BestellingDto> allPlacedOrdersDtos = adminOrderService.getAllPlacedOrders();
        return ResponseEntity.ok(allPlacedOrdersDtos);
    }

    @GetMapping("/order/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
        BestellingDto orderDto = adminOrderService.changeOrderStatus(orderId, status);
        if (orderDto == null)
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }


}
