package nl.EuniqBrillen.BrillenWebshop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import nl.EuniqBrillen.BrillenWebshop.dto.ProductDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    private Long prijs;

    @Lob
    @Column(name = "beschrijving")
    private String beschrijving;

    @Lob
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categorie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore

    private Categorie categorie;

    public ProductDto getProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setNaam(naam);
        productDto.setPrijs(prijs);
        productDto.setBeschrijving(beschrijving);
        productDto.setReturnedImg(img);
        productDto.setCategorieId(categorie.getId());
        productDto.setCategorieNaam(categorie.getNaam());
        return productDto;
    }
}
