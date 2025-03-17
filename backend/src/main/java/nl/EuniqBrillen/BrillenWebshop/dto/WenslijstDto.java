package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

@Data
public class WenslijstDto {
    private Long id;

    private Long userId;

    private Long productId;

    private String productNaam;

    private String productBeschrijving;

    private byte[] returnedImg;

    private Long prijs;
}
