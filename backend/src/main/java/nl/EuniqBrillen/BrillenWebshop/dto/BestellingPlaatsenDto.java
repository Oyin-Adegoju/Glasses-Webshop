package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

@Data
public class BestellingPlaatsenDto {

    private Long userId;

    private String adres;

    private String bestellingBeschrijving;
}
