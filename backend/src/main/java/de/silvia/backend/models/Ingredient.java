package de.silvia.backend.models;

import de.silvia.backend.api.IngredientDto;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @NonNull
    String ingredientName;
    String ingredientAmount;

    public Ingredient(IngredientDto ingredientDto){
        super();
        this.ingredientName = ingredientDto.getIngredientName();
        this.ingredientAmount = ingredientDto.getIngredientAmount();
    }
}
