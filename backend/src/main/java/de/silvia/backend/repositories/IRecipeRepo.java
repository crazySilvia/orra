package de.silvia.backend.repositories;

import de.silvia.backend.models.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecipeRepo extends MongoRepository<Recipe, String> {

    Recipe findRecipeByUserIdAndRecipeName(String userId, String recipeName);

    List<Recipe> findAllByUserId(String userId);

    void deleteRecipeByUserIdAndRecipeName(String userId, String recipeName);
}
