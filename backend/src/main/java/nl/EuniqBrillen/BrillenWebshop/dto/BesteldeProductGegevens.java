package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

@Data
public class BesteldeProductGegevens {

    private Long id;

    private String naam;

    private Long productPrijs;

    private Long hoeveelheid;

    private byte[] returnedImg;
}
