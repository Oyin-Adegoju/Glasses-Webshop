package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.klant.recensie;

import nl.EuniqBrillen.BrillenWebshop.dto.BesteldeProductResponsDto;
import nl.EuniqBrillen.BrillenWebshop.dto.BestellingResponsDto;
import nl.EuniqBrillen.BrillenWebshop.dto.RecensieDto;

import java.io.IOException;

public interface RecensieService {

    BesteldeProductResponsDto getOrderedProductsDetailsByOrderId(Long orderId);

    RecensieDto giveReview(RecensieDto reviewDto) throws IOException;
}
