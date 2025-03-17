package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class TweedeProductDto {
    private Long id;

    private String naam;

    private String beordeeling;

    private Long beschickbaarVooraad;

    private Long prijs;

    private String beschrijving;

    private MultipartFile img;

    private byte[] returnedImg;

    private String categorieId;
}
