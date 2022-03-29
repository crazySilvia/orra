package de.silvia.backend.services;

import de.silvia.backend.api.ArticleDto;
import de.silvia.backend.models.Article;
import de.silvia.backend.models.ArticleList;
import de.silvia.backend.repositories.IArticleListRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ArtikelListServiceTest {

    private final IArticleListRepo artikelListRepo = mock(IArticleListRepo.class);
    private final ArticleListService articleListService = new ArticleListService(artikelListRepo);

    @Test
    void addArtikelList() throws CloneNotSupportedException {
        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listName = "listName";
        ArticleList testArtikelList = ArticleList.newArticleList(listName, artikels, userId);

        when(artikelListRepo.insert(testArtikelList))
                .thenReturn(testArtikelList);

        assertEquals(testArtikelList, articleListService.addArticleList(userId, listName));
    }

    @Test
    void shouldThrowErrorIfAddArtikelList() {
        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listName = "listName";
        ArticleList testArtikelList = ArticleList.newArticleList(listName, artikels, userId);

        when(artikelListRepo.findArticleListByUserIdAndListName(userId, listName))
                .thenReturn(testArtikelList);

        assertThrows(CloneNotSupportedException.class, () -> articleListService.addArticleList(userId, listName));
    }

    @Test
    void deleteArtikelList() {
        String userId = "userId";
        String listId = "listId";

        //doThrow(new IllegalArgumentException()).when(artikelListRepo).deleteArtikelListByUserIdAndListId(userId, listId);
        doNothing().when(artikelListRepo).deleteArticleListByUserIdAndListId(userId, listId);

        articleListService.deleteArticleList(userId, listId);
        verify(artikelListRepo, times(1)).deleteArticleListByUserIdAndListId(userId, listId);
    }

    @Test
    void getAllArtikelLists() {
        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        ArticleList testArtikelList = ArticleList.newArticleList(listId, artikels, userId);
        List<ArticleList> testArtikelListen = List.of(testArtikelList);

        when(artikelListRepo.findAllByUserId(userId))
                .thenReturn(testArtikelListen);

        assertEquals(testArtikelListen, articleListService.getAllArticleLists(userId));
    }

    @Test
    void addArtikel() {
        List<Article> artikels = new java.util.ArrayList<>(Collections.emptyList());
        String userId = "userId";
        String listId = "listId";
        ArticleDto testArtikelDto = new ArticleDto("artikelName", 7, "Einheiten");
        Article testArtikel = new Article(testArtikelDto);
        ArticleList testArtikelList = ArticleList.newArticleList(listId, artikels, userId);
        testArtikelList.addArticle(testArtikel);
        when(artikelListRepo.findArticleListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);
        when(artikelListRepo.save(testArtikelList))
                .thenReturn(testArtikelList);

        articleListService.addArticle(userId, listId, testArtikelDto);
        verify(artikelListRepo, times(1)).save(testArtikelList);
    }

    @Test
    void getArtikelList() {
        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        ArticleList testArtikelList = ArticleList.newArticleList(listId, artikels, userId);

        when(artikelListRepo.findArticleListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);

        assertEquals(testArtikelList, articleListService.getArticleList(userId, listId));
    }

    @Test
    void shouldFailIfGetArtikelList(){
        String userId = "userId";
        String listId = "listId";

        when(artikelListRepo.findArticleListByUserIdAndListId(userId, listId))
                .thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> articleListService.getArticleList(userId, listId));
    }

    @Test
    void deleteArtikel() {
        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        String artikelName = "artikelName";
        ArticleList testArtikelList = ArticleList.newArticleList(listId, artikels, userId);

        when(artikelListRepo.findArticleListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);

        articleListService.deleteArticle(userId, listId, artikelName);
        verify(artikelListRepo, times(1)).save(testArtikelList);
    }

    @Test
    void decreaseArtikel() {
        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        String artikelName = "artikelName";
        ArticleList testArtikelList = ArticleList.newArticleList(listId, artikels, userId);

        when(artikelListRepo.findArticleListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);

        articleListService.decreaseArticle(userId, listId, artikelName);
        verify(artikelListRepo, times(1)).save(testArtikelList);
    }

    @Test
    void increaseArtikel() {
        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        String artikelName = "artikelName";
        ArticleList testArtikelList = ArticleList.newArticleList(listId, artikels, userId);

        when(artikelListRepo.findArticleListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);

        articleListService.increaseArticle(userId, listId, artikelName);
        verify(artikelListRepo, times(1)).save(testArtikelList);
    }
}