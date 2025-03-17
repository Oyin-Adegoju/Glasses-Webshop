package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;
import nl.EuniqBrillen.BrillenWebshop.enums.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
public class BestellingDto {
    private String bestellingBeschrijving;

    private List<WinkelwagenArtikelenDto> winkelwagenArtikelen;

    private Long id;

    private Date datum;

    private UUID trackingId;

    private Long aantal;

    private String adres;

    private OrderStatus status;

    private String betaalen;

    private String gebruikersNaam;

    private Long totaalAantal;

}
