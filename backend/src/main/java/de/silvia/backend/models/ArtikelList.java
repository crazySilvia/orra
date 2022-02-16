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
    private String listId;
    @NonNull
    private String listName;
    private List<Artikel> artikels;
    private String userId;

    public static ArtikelList newArtikelList(String listName, List<Artikel> artikels, String userId) {
        return ArtikelList.builder()
                .listName(listName)
                .artikels(artikels)
                .userId(userId)
                .build();
    }

    public void addArticle(Artikel article){
        artikels.add(article);
    }



}