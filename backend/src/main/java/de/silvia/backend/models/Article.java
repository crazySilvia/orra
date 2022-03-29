package de.silvia.backend.models;

import de.silvia.backend.api.ArticleDto;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    private String articleId;
    @NonNull
    private String name;
    private float amount;
    private String unit;

    public Article(ArticleDto articleDto){
        super();
        this.name = articleDto.getName();
        this.amount = articleDto.getAmount();
        this.unit = articleDto.getUnit();
        this.articleId = UUID.randomUUID().toString();
    }

    public Article decreaseArticle(){
        this.amount--;
        return this;
    }

    public Article increaseArticle(){
        this.amount++;
        return this;
    }
}