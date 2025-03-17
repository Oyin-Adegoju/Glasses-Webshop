package nl.EuniqBrillen.BrillenWebshop.entity;

import jakarta.persistence.*;
import lombok.Data;
import nl.EuniqBrillen.BrillenWebshop.dto.CategorieDto;

@Entity
@Data
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;

    @Lob
    @Column(name = "beschrijving")
    private String beschrijving;

    public void getCategoryEntity(CategorieDto categorieDto) {
        this.naam = categorieDto.getNaam();
        this.beschrijving = categorieDto.getBeschrijving();
    }

    public CategorieDto getCategoryDto() {
        CategorieDto categorieDto = new CategorieDto();
        categorieDto.setId(id);
        categorieDto.setNaam(naam);
        categorieDto.setBeschrijving(beschrijving);
        return categorieDto;
    }
}
