package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;
import nl.EuniqBrillen.BrillenWebshop.enums.Gebruikerrol;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GebruikerDto {
    private Long id;          // Field to store the unique identifier of the user.
    private String email;     // Field to store the email address of the user.
    private String name;      // Field to store the name of the user.
    private Gebruikerrol role; // Field to store the role of the user. It's likely an enum type
    private MultipartFile img;
    private byte[] returnedImg;
}
