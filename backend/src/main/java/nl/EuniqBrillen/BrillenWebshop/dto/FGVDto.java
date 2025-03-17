package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

@Data
public class FGVDto {
    private Long id;

    private String vraag;

    private String antwoord;

    private Long productId;

    private String productNaam;
}
