package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class RecensieDto {
    private Long id;

    private Long beordeling;

    private String beschrijving;

    private MultipartFile img;

    private byte[] returnedImg;

    private Long userId;

    private String gebruikersNaam;

    private Long productId;

}
