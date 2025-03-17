package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

import java.util.List;
@Data
public class BesteldeProductResponsDto {

    private List<BesteldeProductGegevens> besteldeProductGegevensList;

    private Long aantalBestelling;

}
