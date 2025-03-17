package nl.EuniqBrillen.BrillenWebshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class VolleProductDetailsDto {

    private ProductDto productDto;

    private List<FGVDto> fgvDtoList;

    private List<RecensieDto> recensieDtoList;

}
