package de.silvia.backend.repositories;

import de.silvia.backend.models.ArtikelList;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IArtikelListRepo extends MongoRepository<ArtikelList, String> {

    Optional<ArtikelList> findArtikelListByListName(String listName);

    @NotNull List<ArtikelList> findAll();

}