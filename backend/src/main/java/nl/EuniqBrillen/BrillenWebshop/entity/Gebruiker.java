package nl.EuniqBrillen.BrillenWebshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import nl.EuniqBrillen.BrillenWebshop.dto.GebruikerDto;
import nl.EuniqBrillen.BrillenWebshop.enums.Gebruikerrol;
@Entity
@Data
@Table(name = "gebruiker")

public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private Gebruikerrol role;

    @Lob
    private byte[] img;

    public GebruikerDto getGebruikerDto() {
        GebruikerDto gebruikerDto = new GebruikerDto();
        gebruikerDto.setId(id);
        gebruikerDto.setName(name);
        gebruikerDto.setEmail(email);
        gebruikerDto.setReturnedImg(img);
        gebruikerDto.setRole(role);
        return gebruikerDto;
    }
}
