package de.silvia.backend.models;

import de.silvia.backend.api.ArticleDto;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @NonNull
    private String name;
    private float amount;
    private String unit;

    public Article(ArticleDto articleDto){
        super();
        this.name = articleDto.getName();
        this.amount = articleDto.getAmount();
        this.unit = articleDto.getUnit();
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