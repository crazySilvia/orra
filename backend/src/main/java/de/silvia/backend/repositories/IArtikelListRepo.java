package de.silvia.backend.repositories;

import de.silvia.backend.models.ArtikelList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArtikelListRepo extends MongoRepository<ArtikelList, String> {

    List<ArtikelList> findAllByUserId(String userId);

    void deleteArtikelListByUserIdAndListName(String userId, String id);

    ArtikelList findArtikelListByUserIdAndId(String userId, String id);
}