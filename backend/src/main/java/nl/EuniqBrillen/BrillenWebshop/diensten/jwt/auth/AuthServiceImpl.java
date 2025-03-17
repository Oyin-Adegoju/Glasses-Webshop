package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.auth;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import nl.EuniqBrillen.BrillenWebshop.dto.AanmeldenAanvraag;
import nl.EuniqBrillen.BrillenWebshop.dto.GebruikerDto;
import nl.EuniqBrillen.BrillenWebshop.dto.WachtwoordVeranderenDto;
import nl.EuniqBrillen.BrillenWebshop.entity.Gebruiker;
import nl.EuniqBrillen.BrillenWebshop.enums.Gebruikerrol;
import nl.EuniqBrillen.BrillenWebshop.enums.OrderStatus;
import nl.EuniqBrillen.BrillenWebshop.repository.BestellingRepository;
import nl.EuniqBrillen.BrillenWebshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthServices {

    @Autowired
    private UserRepository userRepository; // Autowired field for accessing user data.

    @Autowired
    private BestellingRepository bestellingRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // Autowired field for password encoding.

    @PostConstruct
    public void createAdminAccount() {
        Gebruiker adminAccount = userRepository.findByRole(Gebruikerrol.ADMIN);
        if (null == adminAccount) {
            Gebruiker user = new Gebruiker ();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setRole(Gebruikerrol.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
        }
    }

    @Transactional
    public GebruikerDto createUser(AanmeldenAanvraag aanmeldenAanvraag) {
        Gebruiker gebruiker = new Gebruiker();
        gebruiker.setEmail(aanmeldenAanvraag.getEmail());
        gebruiker.setName(aanmeldenAanvraag.getName());
        gebruiker.setPassword(new BCryptPasswordEncoder().encode(aanmeldenAanvraag.getPassword()));
        gebruiker.setRole(Gebruikerrol.KLANT);
        Gebruiker createdUser = userRepository.save(gebruiker);

        Bestelling bestelling = new Bestelling();
        bestelling .setAantal(0L);
        bestelling .setTotalAmount(0L);
        bestelling .setGebruiker(gebruiker);
        bestelling .setStatus(OrderStatus.Pending);

        bestellingRepository.save(bestelling);
        GebruikerDto createdUserDto = new GebruikerDto();
        createdUserDto.setId(createdUser.getId());
        return createdUserDto;
    }


    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @Override
    public GebruikerDto getUserById(Long userId) {
       GebruikerDto userDto = new GebruikerDto();
        Optional<Gebruiker> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userDto = optionalUser.get().getGebruikerDto();
            userDto.setReturnedImg(optionalUser.get().getImg());
        }
        return userDto;
    }

    @Override
    public GebruikerDto updateUser(GebruikerDto gebruikerDto) throws IOException {
        Optional<Gebruiker> userOptional = userRepository.findById(gebruikerDto.getId());
        if (userOptional.isPresent()) {
            Gebruiker user = userOptional.get();
            user.setEmail(gebruikerDto.getEmail());
            user.setName(gebruikerDto.getName());
            if(gebruikerDto.getImg() != null){
                user.setImg(gebruikerDto.getImg().getBytes());
            }

            Gebruiker updatedUser = userRepository.save(user);
            GebruikerDto geupdatedGebruikerDto = new GebruikerDto();
            geupdatedGebruikerDto.setId(updatedUser.getId());
            return geupdatedGebruikerDto;
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<?> updatePasswordById(WachtwoordVeranderenDto wachtwoordVeranderenDto) {
        Gebruiker user = null;
        try {
            Optional<Gebruiker> userOptional = userRepository.findById(wachtwoordVeranderenDto.getId());
            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (this.bCryptPasswordEncoder.matches(wachtwoordVeranderenDto.getOudWachtwoord(), user.getPassword())) {
                    user.setPassword(bCryptPasswordEncoder.encode(wachtwoordVeranderenDto.getNieuwWachtwoord()));
                    Gebruiker updateUser = userRepository.save(user);
                    GebruikerDto userDto = new GebruikerDto();
                    userDto.setId(updateUser.getId());
                    return ResponseEntity.status(HttpStatus.OK).body(userDto);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Oud wachtwoord is fout");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gebruiker niet gevonden");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
