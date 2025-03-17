package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.auth;

 // Publieke interface voor authenticatiediensten.

import nl.EuniqBrillen.BrillenWebshop.dto.AanmeldenAanvraag;
import nl.EuniqBrillen.BrillenWebshop.dto.GebruikerDto;
import nl.EuniqBrillen.BrillenWebshop.dto.WachtwoordVeranderenDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthServices {
    GebruikerDto createUser(AanmeldenAanvraag aanmeldenAanvraag);
    Boolean hasUserWithEmail(String email);
    GebruikerDto getUserById(Long userId);

    GebruikerDto updateUser(GebruikerDto userDto) throws IOException;

    ResponseEntity<?> updatePasswordById(WachtwoordVeranderenDto wachtwoordVeranderenDto);
}
