package nl.EuniqBrillen.BrillenWebshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import nl.EuniqBrillen.BrillenWebshop.dto.WenslijstDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Wenslijst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public WenslijstDto getWenslijstDto() {
        WenslijstDto wenslijstDto = new WenslijstDto();
        wenslijstDto.setId(id);
        wenslijstDto.setProductId(product.getId());
        wenslijstDto.setReturnedImg(product.getImg());
        wenslijstDto.setProductNaam(product.getNaam());
        wenslijstDto.setProductBeschrijving(product.getBeschrijving());
        wenslijstDto.setPrijs(product.getPrijs());
        wenslijstDto.setUserId(gebruiker.getId());
        return wenslijstDto;
    }

}
