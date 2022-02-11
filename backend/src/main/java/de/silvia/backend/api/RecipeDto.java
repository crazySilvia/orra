package de.silvia.backend.api;

import de.silvia.backend.models.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    @Id
    @NotNull
    private String recipeName;
    private List<Ingredient> ingredientList;
    private String recipeText;
}