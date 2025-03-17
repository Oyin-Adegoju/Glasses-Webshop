package nl.EuniqBrillen.BrillenWebshop.repository;

import nl.EuniqBrillen.BrillenWebshop.entity.Recensie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecensieRepository extends JpaRepository<Recensie, Long> {
    List<Recensie> findAllByProductId(Long productId);
}
