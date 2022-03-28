package de.silvia.backend.services;

import de.silvia.backend.api.ArticleDto;
import de.silvia.backend.models.Article;
import de.silvia.backend.models.ArticleList;
import de.silvia.backend.repositories.IArticleListRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArticleListService {

    private final IArticleListRepo articleListRepo;
    private static final Log LOG = LogFactory.getLog(ArticleListService.class);

    public ArticleListService(IArticleListRepo articleListRepo) {
        this.articleListRepo = articleListRepo;
    }

    public ArticleList addArticleList(String userId, String listName) throws CloneNotSupportedException {
        if (articleListRepo.findArticleListByUserIdAndListId(userId, listName) != null) {
            throw new CloneNotSupportedException("Liste mit Namen " + listName + " gibt es schon!");
        }
        List<Article> listOfArticles = Collections.emptyList();
        LOG.info("Liste mit Namen " + listName + " hinzugefügt!");
        return articleListRepo.insert(ArticleList.newArticleList(listName, listOfArticles, userId));
    }

    public void deleteArticleList(String userId, String listId) {
        articleListRepo.deleteArticleListByUserIdAndListId(userId, listId);
    }

    public List<ArticleList> getAllArticleLists(String userId) {
        return articleListRepo.findAllByUserId(userId);
    }

    public ArticleList addArticle(String userId, String listId, ArticleDto articleDto) {
        final Article article = new Article(articleDto);
        ArticleList articleList = getArticleList(userId, listId);
        articleList.addArticle(article);
        return articleListRepo.save(articleList);
    }

    public ArticleList getArticleList(String userId, String listId) {
        ArticleList articleList = articleListRepo.findArticleListByUserIdAndListId(userId, listId);
        if (articleList == null) {
            throw new NoSuchElementException("Liste mit ListId " + listId + " nicht gefunden!");
        }
        return articleList;
    }

    public void deleteArticle(String userId, String listId, String articleName) {
        ArticleList articleList = getArticleList(userId, listId);
        List<Article> updateArticle = articleList.getListOfArticles()
                .stream()
                .filter((article) -> (!article.getName().equals(articleName)))
                .toList();
        articleList.setListOfArticles(updateArticle);
        articleListRepo.save(articleList);
    }

    public void decreaseArticle(String userId, String listId, String articleName) {
        ArticleList articleList = getArticleList(userId, listId);
        List<Article> artList = articleList.getListOfArticles()
                .stream()
                .map((article) -> (article.getName().equals(articleName)) ? article.decreaseArticle() : article)
                .toList();
        articleList.setListOfArticles(artList);
        articleListRepo.save(articleList);
    }

    public void increaseArticle(String userId, String listId, String articleName) {
        ArticleList articleList = getArticleList(userId, listId);
        List<Article> artList = articleList.getListOfArticles()
                .stream()
                .map((article) -> (article.getName().equals(articleName)) ? article.increaseArticle() : article)
                .toList();
        articleList.setListOfArticles(artList);
        articleListRepo.save(articleList);
    }
}