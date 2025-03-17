package nl.EuniqBrillen.BrillenWebshop.repository;

import nl.EuniqBrillen.BrillenWebshop.entity.FGV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FGVRepository extends JpaRepository<FGV, Long> {
    List<FGV> findAllByProductId(Long productId);
}
