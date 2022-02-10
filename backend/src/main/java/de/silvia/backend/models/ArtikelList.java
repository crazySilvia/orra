package de.silvia.backend.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ArtikelList {
    @Id
    @NonNull
    private String listName;
    private List<Artikel> artikelList;

    public static ArtikelList newArtikelList(String listName, List<Artikel> artikelList) {
        return ArtikelList.builder()
                .listName(listName)
                .artikelList(artikelList)
                .build();
    }

    public void addArticle(Artikel article){
        artikelList.add(article);
    }
}