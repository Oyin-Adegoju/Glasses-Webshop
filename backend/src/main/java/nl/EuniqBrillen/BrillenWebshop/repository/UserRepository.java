package nl.EuniqBrillen.BrillenWebshop.repository;

import nl.EuniqBrillen.BrillenWebshop.entity.Gebruiker;
import nl.EuniqBrillen.BrillenWebshop.enums.Gebruikerrol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Gebruiker, Long> {
    Optional<Gebruiker> findFirstByEmail(String email);
    Gebruiker findByRole(Gebruikerrol role);
    List<Gebruiker>findAllByRole(Gebruikerrol role);
}
