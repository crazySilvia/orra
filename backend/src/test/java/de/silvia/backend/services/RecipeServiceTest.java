package de.silvia.backend.services;

import de.silvia.backend.api.IngredientDto;
import de.silvia.backend.api.RecipeDto;
import de.silvia.backend.models.Ingredient;
import de.silvia.backend.models.Recipe;
import de.silvia.backend.repositories.IRecipeRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

    private final IRecipeRepo testRecipeRepo = mock(IRecipeRepo.class);
    private final RecipeService testRecipeService = new RecipeService(testRecipeRepo);

    @Test
    void addRecipe() throws CloneNotSupportedException {
        List<Ingredient> ingredientList = Collections.emptyList();
        String userId = "userId";
        RecipeDto testRecipeDto = new RecipeDto("Rezeptname", ingredientList, "Rezept Text");
        Recipe testRecipe = new Recipe(userId, testRecipeDto);

        when(testRecipeRepo.insert(testRecipe))
                .thenReturn(testRecipe);

        assertEquals(testRecipe, testRecipeService.addRecipe(userId, testRecipeDto));
    }

    @Test
    void shouldThrowErrorIfAddRecipe(){
        List<Ingredient> ingredientList = Collections.emptyList();
        String userId = "userId";
        RecipeDto testRecipeDto = new RecipeDto("Rezeptname", ingredientList, "Rezept Text");
        Recipe testRecipe = new Recipe(userId, testRecipeDto);

        when(testRecipeRepo.findRecipeByUserIdAndRecipeName(userId, "Rezeptname"))
                .thenReturn(testRecipe);

        assertThrows(CloneNotSupportedException.class, () -> testRecipeService.addRecipe(userId, testRecipeDto));
    }

    @Test
    void deleteRecipe() {

        String testUserId = "userId";
        String testRecipeName = "rezeptname";

        doNothing().when(testRecipeRepo).deleteRecipeByUserIdAndRecipeName(testUserId, testRecipeName);

        testRecipeService.deleteRecipe(testUserId, testRecipeName);
        verify(testRecipeRepo, times(1)).deleteRecipeByUserIdAndRecipeName(testUserId, testRecipeName);
    }

    @Test
    void getAllRecipes() {
        List<Ingredient> ingredientList = Collections.emptyList();
        String userId = "userId";
        RecipeDto testRecipeDto = new RecipeDto("Rezeptname", ingredientList, "Rezept Text");
        Recipe testRecipe = new Recipe(userId, testRecipeDto);
        List<Recipe> testListOfRecipes = List.of(testRecipe);

        when(testRecipeRepo.findAllByUserId(userId))
                .thenReturn(testListOfRecipes);

        assertEquals(testListOfRecipes, testRecipeService.getAllRecipes(userId));
    }

    @Test
    void getRecipe() {
        List<Ingredient> ingredientList = Collections.emptyList();
        String userId = "userId";
        RecipeDto testRecipeDto = new RecipeDto("Rezeptname", ingredientList, "Rezept Text");
        Recipe testRecipe = new Recipe(userId, testRecipeDto);

        when(testRecipeRepo.findRecipeByUserIdAndRecipeName(userId, testRecipeDto.getRecipeName()))
                .thenReturn(testRecipe);

        assertEquals(testRecipe, testRecipeService.getRecipe(userId, testRecipeDto.getRecipeName()));
    }

    @Test
    void shouldFailGetRecipe(){
        String testUserId = "userId";
        String testRecipeName = "Rezeptname";

        when(testRecipeRepo.findRecipeByUserIdAndRecipeName(testUserId, testRecipeName))
                .thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> testRecipeService.getRecipe(testUserId, testRecipeName));
    }

    @Test
    void addIngredient() {
        List<Ingredient> testIngredientList = new java.util.ArrayList<>(Collections.emptyList());
        String userId = "userId";
        RecipeDto testRecipeDto = new RecipeDto("Rezeptname", testIngredientList, "Rezept Text");
        Recipe testRecipe = new Recipe(userId, testRecipeDto);
        IngredientDto testIngredientDto = new IngredientDto("Mehl", "2 Teelöffel" );

        when(testRecipeRepo.findRecipeByUserIdAndRecipeName(userId, testRecipe.getRecipeName()))
                .thenReturn(testRecipe);

        testRecipeService.addIngredient(userId, testRecipe.getRecipeName(), testIngredientDto);
        verify(testRecipeRepo, times(1)).save(testRecipe);
    }

    @Test
    void deleteIngredient() {
        List<Ingredient> testIngredientList = Collections.emptyList();
        String userId = "userId";
        String testRecipeName = "TestRezeptname";
        RecipeDto testRecipeDto = new RecipeDto("Rezeptname", testIngredientList, "Rezept Text");
        Recipe testRecipe = new Recipe(userId, testRecipeDto);
        String testIngredientName = "Zutatname";

        when(testRecipeRepo.findRecipeByUserIdAndRecipeName(userId, testRecipeName))
                .thenReturn(testRecipe);

        testRecipeService.deleteIngredient(userId, testRecipeName, testIngredientName);
        verify(testRecipeRepo, times(1)).save(testRecipe);

    }
}