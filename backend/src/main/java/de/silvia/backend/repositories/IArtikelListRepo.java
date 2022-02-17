package de.silvia.backend.repositories;

import de.silvia.backend.models.ArtikelList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArtikelListRepo extends MongoRepository<ArtikelList, String> {

    List<ArtikelList> findAllByUserId(String userId);

    void deleteArtikelListByUserIdAndListId(String userId, String listId);

    ArtikelList findArtikelListByUserIdAndListId(String userId, String listId);


}