package nl.EuniqBrillen.BrillenWebshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import nl.EuniqBrillen.BrillenWebshop.dto.WinkelwagenArtikelenDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@Entity
@Data
public class WinkelwagenArtikelen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long prijs;

    private Long hoeveelheid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Gebruiker gebruiker;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Bestelling bestelling;

    public WinkelwagenArtikelenDto getWinkelwagenArtikelenDto() {
        WinkelwagenArtikelenDto winkelwagenArtikelenDto= new WinkelwagenArtikelenDto();
        winkelwagenArtikelenDto.setId(id);
        winkelwagenArtikelenDto.setPrijs(prijs);
        winkelwagenArtikelenDto.setProductId(product.getId());
        winkelwagenArtikelenDto.setHoeveelheid(hoeveelheid);
        winkelwagenArtikelenDto.setUserId(gebruiker.getId());
        winkelwagenArtikelenDto.setProductNaam(product.getNaam());
        winkelwagenArtikelenDto.setReturnedImg(product.getImg());
        return  winkelwagenArtikelenDto;
    }
}
