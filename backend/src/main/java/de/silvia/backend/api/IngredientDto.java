package de.silvia.backend.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {

    @Id
    @NotNull
    private String ingredientName;
    private String ingredientAmount;
}