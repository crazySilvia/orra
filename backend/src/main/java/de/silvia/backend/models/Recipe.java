package de.silvia.backend.models;

import de.silvia.backend.api.RecipeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Recipe {

    @Id
    @NotNull
    private String recipeName;
    private List<Ingredient> ingredientList;
    private String recipeText;
    private String userId;

    public Recipe(String userId, RecipeDto recipeDto){
        super();
        this.recipeName = recipeDto.getRecipeName();
        this.ingredientList = recipeDto.getIngredientList();
        this.recipeText = recipeDto.getRecipeText();
        this.userId = userId;
    }

    public void addIngredient(Ingredient ingredient){

        ingredientList.add(ingredient);
    }
}
