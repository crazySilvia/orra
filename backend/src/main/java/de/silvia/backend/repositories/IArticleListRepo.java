package de.silvia.backend.repositories;

import de.silvia.backend.models.ArticleList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArticleListRepo extends MongoRepository<ArticleList, String> {

    List<ArticleList> findAllByUserId(String userId);

    void deleteArticleListByUserIdAndListId(String userId, String listId);

    ArticleList findArticleListByUserIdAndListId(String userId, String listId);


}