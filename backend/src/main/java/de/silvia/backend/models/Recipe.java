package de.silvia.backend.models;

import de.silvia.backend.api.RecipeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Recipe {

    @Id
    @NotNull
    private String recipeName;
    private List<Ingredient> ingredientList;
    private String recipeText;

    public Recipe(RecipeDto recipeDto){
        super();
        this.recipeName = recipeDto.getRecipeName();
        this.ingredientList = recipeDto.getIngredientList();
        this.recipeText = recipeDto.getRecipeText();
    }

    public void addIngredient(Ingredient ingredient){

        ingredientList.add(ingredient);
    }
}
