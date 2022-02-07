package de.silvia.backend.models;

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
    private int anzahl;

    public static Artikel newArtikel(String name, int anzahl) {
        return Artikel.builder()
                .name(name)
                .anzahl(anzahl)
                .build();
    }
}