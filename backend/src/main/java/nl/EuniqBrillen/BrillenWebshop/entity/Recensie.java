package nl.EuniqBrillen.BrillenWebshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import nl.EuniqBrillen.BrillenWebshop.dto.RecensieDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Recensie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long beordeling;

    @Lob
    @Column(name = "beschrijving")
    private String description;

    @Lob
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Gebruiker gebruiker;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    public RecensieDto getRecensieDto() {
        RecensieDto recensieDto = new RecensieDto();
        recensieDto .setId(id);
        recensieDto .setBeordeling(beordeling);
        recensieDto .setBeschrijving(description);
        recensieDto .setReturnedImg(img);
        recensieDto .setProductId(product.getId());
        recensieDto .setUserId(gebruiker.getId());
        recensieDto .setGebruikersNaam(gebruiker.getName());
        return recensieDto ;
    }
}
