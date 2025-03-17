package nl.EuniqBrillen.BrillenWebshop.repository;

import nl.EuniqBrillen.BrillenWebshop.entity.Wenslijst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WensLijstRepository extends JpaRepository<Wenslijst, Long> {
    List<Wenslijst> findAllByUser_Id(Long userId);
}
