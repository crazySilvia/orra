package de.silvia.backend.controller;

import de.silvia.backend.api.RecipeDto;
import de.silvia.backend.models.Recipe;
import de.silvia.backend.services.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<Recipe> getAll(){
        return recipeService.getAllRecipes();
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody RecipeDto recipeDto)throws CloneNotSupportedException{
        return recipeService.addRecipe(recipeDto);
    }
}
