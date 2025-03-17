package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.admin.bestellingenadmin;

import lombok.RequiredArgsConstructor;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import nl.EuniqBrillen.BrillenWebshop.enums.OrderStatus;
import nl.EuniqBrillen.BrillenWebshop.repository.BestellingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminBestellingServiceImpl implements AdminBestellingService{
    private final BestellingRepository bestellingRepository;

    @Override
    public List<BestellingDto> getAllPlacedOrders() {
        List<Bestelling> orderList = bestellingRepository.findAllByStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered));
        return orderList.stream().map(order -> {
            BestellingDto bestellingDto = new BestellingDto();
            bestellingDto.setId(order.getId());
            bestellingDto.setBestellingBeschrijving(order.getBestellingBeschrijving());
            bestellingDto.setDatum(order.getDatum());
            bestellingDto.setTrackingId(order.getTrackingId());
            bestellingDto.setAantal(order.getAantal());
            bestellingDto.setBetaalen(order.getBetaalen());
            bestellingDto.setAdres(order.getAdres());
            bestellingDto.setStatus(order.getStatus());
            bestellingDto.setGebruikersNaam(order.getGebruiker().getName());
            return bestellingDto;
        }).collect(Collectors.toList());
    }

    @Override
    public BestellingDto changeOrderStatus(Long orderId, String status) {
        Optional<Bestelling> optionalBestelling = bestellingRepository.findById(orderId);
        if (optionalBestelling.isPresent()) {
            Bestelling bestelling = optionalBestelling.get();
            if (Objects.equals(status, "Shipped")) {
                bestelling.setStatus(OrderStatus.Shipped);
            } else {
                bestelling.setStatus(OrderStatus.Delivered);
            }
            Bestelling order1 = bestellingRepository.save(bestelling);
            BestellingDto bestellingDto = new BestellingDto();
            bestellingDto.setId(order1.getId());
            return bestellingDto;
        }
        return null;
    }


}
