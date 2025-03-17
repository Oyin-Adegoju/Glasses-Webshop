package nl.EuniqBrillen.BrillenWebshop.repository;

import nl.EuniqBrillen.BrillenWebshop.entity.WinkelwagenArtikelen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WinkelmandRepository extends JpaRepository<WinkelwagenArtikelen,Long> {
    Optional<WinkelwagenArtikelen> findByProductIdAndOrderIdAndUserId(Long productId, Long orderId, Long userId);

    List<WinkelwagenArtikelen> findByOrderId(Long orderId);

}
