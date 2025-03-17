package nl.EuniqBrillen.BrillenWebshop.diensten.jwt.fgv;

import nl.EuniqBrillen.BrillenWebshop.dto.FGVDto;

public interface FGVService {
    FGVDto postFGV(Long productId, FGVDto fgvDto);
}
