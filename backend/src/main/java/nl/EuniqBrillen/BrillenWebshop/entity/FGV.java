package nl.EuniqBrillen.BrillenWebshop.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import nl.EuniqBrillen.BrillenWebshop.dto.FGVDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class FGV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vraag;

    private String antwoord;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    public FGVDto getFGVDto() {
        FGVDto fgvDto = new FGVDto();
        fgvDto.setId(id);
        fgvDto.setVraag(vraag);
        fgvDto.setAntwoord(getAntwoord());
        fgvDto.setProductId(product.getId());
        fgvDto.setProductNaam(product.getNaam());
        return fgvDto;
    }


}
