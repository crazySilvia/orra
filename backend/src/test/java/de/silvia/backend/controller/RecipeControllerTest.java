package de.silvia.backend.controller;

import de.silvia.backend.api.IngredientDto;
import de.silvia.backend.api.RecipeDto;
import de.silvia.backend.models.Ingredient;
import de.silvia.backend.models.Recipe;
import de.silvia.backend.repositories.IRecipeRepo;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import de.silvia.backend.security.services.UserService;
import de.silvia.backend.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RecipeControllerTest {

    private final IRecipeRepo testRecipeRepo = mock(IRecipeRepo.class);
    private final RecipeService mockedRecipeService = mock(RecipeService.class);
    private final IUserRepo testUserRepo = mock(IUserRepo.class);
    private final UserService testUserService = new UserService(testUserRepo);
    private final RecipeController testRecipeController =
            new RecipeController(mockedRecipeService, testUserService);

    @Test
    void getAll() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        List<Ingredient> ingredientList = Collections.emptyList();
        String userId = "userId";
        RecipeDto testRecipeDto = new RecipeDto("Rezeptname", ingredientList, "Rezept Text");
        Recipe testRecipe = new Recipe(userId, testRecipeDto);
        List<Recipe> testListOfRecipes = List.of(testRecipe);
        //when
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedRecipeService.getAllRecipes(testUser.getUsername()))
                .thenReturn(testListOfRecipes);
        //Then
        assertEquals(testListOfRecipes, testRecipeController.getAll(testPrincipal));
    }

    @Test
    void addRecipe() throws CloneNotSupportedException {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        List<Ingredient> ingredientList = Collections.emptyList();
        RecipeDto testRecipeDto = new RecipeDto("TestRezept", ingredientList, "rezeptText");
        Recipe testRecipe = Recipe.builder().userId(testUser.getUsername()).recipeName("RezeptName")
                .ingredientList(ingredientList).recipeText("Rezept Text").build();
        //when
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedRecipeService.addRecipe(testUser.getUsername(), testRecipeDto))
                .thenReturn(testRecipe);
        //Then
        assertEquals(testRecipe, testRecipeController.addRecipe(testPrincipal, testRecipeDto));
    }

    @Test
    void deleteRecipe() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        String testRecipeName = "Rezeptname";
        //When
        when(testUserRepo.findUserByUsername("Susi"))
                .thenReturn(testUser);
        //Then
        testRecipeController.deleteRecipe(testPrincipal, testRecipeName);
        verify(mockedRecipeService, times(1))
                .deleteRecipe(testUser.getUsername(), testRecipeName);
    }

    @Test
    void addIngredient() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        String testRecipeName = "Rezeptname";
        List<Ingredient> ingredientList = new java.util.ArrayList<>(Collections.emptyList());
        RecipeDto testRecipeDto = new RecipeDto("TestRezept", ingredientList, "rezeptText");
        IngredientDto testIngredientDto = new IngredientDto("Mehl", "2 Teelöffel");
        Ingredient testIngredient = new Ingredient(testIngredientDto);
        ingredientList.add(testIngredient);
        Recipe testRecipe = new Recipe(testUser.getUsername(), testRecipeDto);
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedRecipeService.addIngredient(testUser.getUsername(), testRecipeName, testIngredientDto))
                .thenReturn(testRecipe);
        //Then
        assertTrue(testRecipeController.addIngredient(testPrincipal, testIngredientDto, testRecipeName)
                .getIngredientList().contains(testIngredient));
    }

    @Test
    void delIngredient() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        String testRecipeName = "Rezeptname";
        String testIngredientName = "Zutatname";
        List<Ingredient> ingredientList = Collections.emptyList();
        RecipeDto testRecipeDto = new RecipeDto("TestRezept", ingredientList, "rezeptText");
        Recipe testRecipe = new Recipe(testUser.getUsername(), testRecipeDto);
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(testRecipeRepo.findRecipeByUserIdAndRecipeName(testUser.getUsername(), testRecipeName))
                .thenReturn(testRecipe);
        //Then
        testRecipeController.delIngredient(testPrincipal, testIngredientName, testRecipeName);
        verify(mockedRecipeService, times(1))
                .deleteIngredient(testUser.getUsername(), testRecipeName, testIngredientName);
    }
}