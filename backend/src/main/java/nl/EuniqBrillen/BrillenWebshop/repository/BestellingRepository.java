package nl.EuniqBrillen.BrillenWebshop.repository;

import nl.EuniqBrillen.BrillenWebshop.entity.Gebruiker;
import nl.EuniqBrillen.BrillenWebshop.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface BestellingRepository extends JpaRepository<Bestelling,Long> {
    Bestelling findByGebruikerAndStatus(Gebruiker gebruiker, OrderStatus status);

    Bestelling findByUserIdAndStatus(Long userId, OrderStatus status);

    List<Bestelling> findAllByUserIdAndStatusIn(Long userId, List<OrderStatus> orderStatusList);

    List<Bestelling> findAllByStatusIn(List<OrderStatus> orderStatusList);

    Optional<Bestelling> findByTrackingId(UUID trackingId);



}
