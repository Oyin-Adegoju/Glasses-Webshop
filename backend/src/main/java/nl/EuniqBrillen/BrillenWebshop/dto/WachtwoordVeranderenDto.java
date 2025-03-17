package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

@Data
public class WachtwoordVeranderenDto {
    private Long id;

    private String oudWachtwoord;

    private String nieuwWachtwoord;
}
