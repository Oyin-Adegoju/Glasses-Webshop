package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;



@Data
public class WinkelwagenArtikelenDto {
    private Long id;

    private Long prijs;

    private Long hoeveelheid;

    private Long productId;

    private Long orderId;

    private String productNaam;

    private byte[] returnedImg;

    private Long userId;
}
