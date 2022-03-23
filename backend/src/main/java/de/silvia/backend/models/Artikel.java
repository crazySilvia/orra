package de.silvia.backend.models;

import de.silvia.backend.api.ArtikelDto;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artikel {
    @Id
    @NonNull
    private String name;
    private float anzahl;
    private String unit;

    public Artikel(ArtikelDto artikelDto){
        super();
        this.name = artikelDto.getName();
        this.anzahl = artikelDto.getAnzahl();
        this.unit = artikelDto.getUnit();
    }

    public Artikel decreaseArtikel(){
        this.anzahl--;
        return this;
    }

    public Artikel increaseArtikel(){
        this.anzahl++;
        return this;
    }
}