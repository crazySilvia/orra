package de.silvia.backend.repositories;

import de.silvia.backend.models.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IRecipeRepo extends MongoRepository<Recipe, String> {

    Optional<Recipe> findRecipeByRecipeName(String recipeName);
}
