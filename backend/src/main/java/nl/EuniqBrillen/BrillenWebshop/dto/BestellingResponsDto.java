package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

import java.util.Date;
@Data
public class BestellingResponsDto {
    private float aantal;

    private Date datum;

    private String bestellingBeschrijving;

    private Long orderId;
}
