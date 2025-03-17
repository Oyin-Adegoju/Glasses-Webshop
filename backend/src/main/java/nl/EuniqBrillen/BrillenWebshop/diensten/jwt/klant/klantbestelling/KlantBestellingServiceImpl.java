package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.klantbestelling;

import nl.EuniqBrillen.BrillenWebshop.dto.BestellingDto;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingPlaatsenDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Gebruiker;
import nl.EuniqBrillen.BrillenWebshop.enums.OrderStatus;
import nl.EuniqBrillen.BrillenWebshop.repository.BestellingRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class KlantBestellingServiceImpl implements KlantBestellingService {
    @Autowired
    private BestellingRepository bestellingRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public BestellingDto PlaceOrder(BestellingPlaatsenDto bestellingPlaatsenDto) {
        Bestelling bestelling= bestellingRepository.findByUserIdAndStatus(bestellingPlaatsenDto.getUserId(), OrderStatus.Pending);
        Optional<Gebruiker> optionalUser = userRepository.findById(bestellingPlaatsenDto.getUserId());
        if (optionalUser.isPresent()) {
            bestelling.setBestellingBeschrijving(bestellingPlaatsenDto.getBestellingBeschrijving());
            bestelling.setStatus(OrderStatus.Placed);
            bestelling.setDatum(new Date());
            bestelling.setTrackingId(UUID.randomUUID());
            bestelling.setAdres(bestellingPlaatsenDto.getAdres());
            bestelling.setAantal(bestelling.getAantal());
            bestellingRepository.save(bestelling);
            Gebruiker gebruiker = optionalUser.get();
            Bestelling newOrder = new Bestelling();
            newOrder.setAantal(0L);
            newOrder.setTotalAmount(0L);
            newOrder.setDiscount(0L);
            newOrder.setGebruiker(gebruiker);
            newOrder.setStatus(OrderStatus.Pending);
            bestellingRepository.save(newOrder);
            return bestelling.getBestellingDto();
        }
        return null;
    }

    @Override
    public List<BestellingDto> getMyPlacedOrders(Long userId) {
        return bestellingRepository.findAllByUserIdAndStatusIn(userId, List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered)).stream().map(Bestelling::getBestellingDto).collect(Collectors.toList());
    }

    @Override
    public BestellingDto searchOrderByTrackingId(UUID trackingId) {
        Optional<Bestelling> optionalOrder = bestellingRepository.findByTrackingId(trackingId);
        if (optionalOrder.isPresent()) {
            Bestelling order = optionalOrder.get();
            return order.getBestellingDto();
        }
        return null;
    }
}
