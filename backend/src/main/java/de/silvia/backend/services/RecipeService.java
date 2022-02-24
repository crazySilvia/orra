package de.silvia.backend.services;

import de.silvia.backend.api.IngredientDto;
import de.silvia.backend.api.RecipeDto;
import de.silvia.backend.models.Ingredient;
import de.silvia.backend.models.Recipe;
import de.silvia.backend.repositories.IRecipeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RecipeService {

    private final IRecipeRepo recipeRepo;

    public RecipeService(IRecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public Recipe addRecipe(String userId, RecipeDto recipeDto) throws CloneNotSupportedException {
        if (recipeRepo.findRecipeByUserIdAndRecipeName(userId, recipeDto.getRecipeName()) != null) {
            throw new CloneNotSupportedException("Rezept " + recipeDto.getRecipeName() + " gibt es schon!");
        }
        final Recipe recipe = new Recipe(userId, recipeDto);
        return recipeRepo.insert(recipe);
    }

    public void deleteRecipe(String userId, String recipeName){
        recipeRepo.deleteRecipeByUserIdAndRecipeName(userId, recipeName);
    }

    public List<Recipe> getAllRecipes(String userId) {
        return recipeRepo.findAllByUserId(userId);
    }

    public Recipe getRecipe(String userId, String recipeName){
        Recipe recipe = recipeRepo.findRecipeByUserIdAndRecipeName(userId, recipeName);
        if(recipe == null){
            throw new NoSuchElementException("Rezept: " + recipeName + " nicht gefunden!");
        }
        return recipe;
    }

    public Recipe addIngredient(String userId, String recipeName, IngredientDto ingredientDto){
        final Ingredient ingredient = new Ingredient(ingredientDto);
        Recipe recipe = getRecipe(userId, recipeName);
        recipe.addIngredient(ingredient);
        return recipeRepo.save(recipe);
    }

    public void deleteIngredient(String userId, String recipeName, String ingredientName){
        Recipe recipe = getRecipe(userId, recipeName);
        List<Ingredient> updateIngredients = recipe.getIngredientList()
                .stream()
                .filter((ingredient -> (!ingredient.getIngredientName().equals(ingredientName))))
                .toList();
        recipe.setIngredientList(updateIngredients);

    }

}
