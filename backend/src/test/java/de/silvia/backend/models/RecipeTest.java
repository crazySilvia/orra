package de.silvia.backend.models;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class RecipeTest {

    @Test
    void addIngredient() {
        List<Ingredient> testIngredients = new java.util.ArrayList<>(Collections.emptyList());

        Recipe testRecipe = Recipe.builder().userId("userid").recipeName("Kuchen").
                ingredientList(testIngredients)
                .recipeText("Blabla")
                .build();
        Ingredient testIngredient = Ingredient.builder()
                .ingredientName("Testzutat")
                .ingredientAmount("5 Eßlöffel")
                .build();
        testRecipe.addIngredient(testIngredient);
        assertFalse(testRecipe.getIngredientList().isEmpty());
    }
}