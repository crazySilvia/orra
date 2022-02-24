package de.silvia.backend.controller;

import de.silvia.backend.api.IngredientDto;
import de.silvia.backend.api.RecipeDto;
import de.silvia.backend.models.Recipe;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.services.UserService;
import de.silvia.backend.services.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final UserService userService;

    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    private User getUser(Principal principal) throws ResponseStatusException {
        try {
            return userService.getUserByPrincipal(principal);
        }catch (UsernameNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No User found");
        }
    }

    @GetMapping
    public List<Recipe> getAll(Principal principal){
        User user = getUser(principal);
        return recipeService.getAllRecipes(user.getUsername());
    }

    @PostMapping
    public Recipe addRecipe(Principal principal, @RequestBody RecipeDto recipeDto)throws CloneNotSupportedException{
        User user = getUser(principal);
        return recipeService.addRecipe(user.getUsername(), recipeDto);
    }

    @DeleteMapping("/{recipeName}")
    public void deleteRecipe(Principal principal, @PathVariable String recipeName){
        User user = getUser(principal);
        recipeService.deleteRecipe(user.getUsername(), recipeName);
    }

    @PostMapping("/{recipeName}")
    public Recipe addIngredient(Principal principal, @RequestBody IngredientDto ingredientDto,
                                @PathVariable String recipeName){
        User user = getUser(principal);
        return recipeService.addIngredient(user.getUsername(), recipeName, ingredientDto);
    }

    @DeleteMapping(value = "/{recipeName}/remove/{ingredientName}")
    public void delIngredient(Principal principal, @PathVariable String ingredientName, @PathVariable String recipeName){
        User user = getUser(principal);
        recipeService.deleteIngredient(user.getUsername(), recipeName, ingredientName);
    }
}
