package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ProductDto {
    private Long id;

    private String naam;

    private Long prijs;

    private String beschrijving;

    private MultipartFile img;

    private byte[] returnedImg;

    private Long categorieId;

    private String categorieNaam;
}
