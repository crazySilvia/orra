package de.silvia.backend.services;

import de.silvia.backend.api.RecipeDto;
import de.silvia.backend.models.Recipe;
import de.silvia.backend.repositories.IRecipeRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final IRecipeRepo recipeRepo;
    private static final Log LOG = LogFactory.getLog(ArtikelListService.class);

    public RecipeService(IRecipeRepo recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    public Recipe addRecipe(RecipeDto recipeDto) throws CloneNotSupportedException {
        if(recipeRepo.findRecipeByRecipeName(recipeDto.getRecipeName()).isPresent()){
            throw new CloneNotSupportedException("Rezept " + recipeDto.getRecipeName() + " gibt es schon!");
        }
        final Recipe recipe = new Recipe(recipeDto);
        LOG.info("Rezept " + recipeDto.getRecipeName() + " hinzugefügt!");
        recipeRepo.insert(recipe);
        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }
}
