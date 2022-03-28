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
public class ArticleList {
    @Id
    private String listId;
    @NonNull
    private String listName;
    private List<Article> listOfArticles;
    private String userId;

    public static ArticleList newArticleList(String listName, List<Article> listOfArticles, String userId) {
        return ArticleList.builder()
                .listName(listName)
                .listOfArticles(listOfArticles)
                .userId(userId)
                .build();
    }

    public void addArticle(Article article){
        listOfArticles.add(article);
    }

}