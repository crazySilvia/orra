package de.silvia.backend.controller;

import de.silvia.backend.api.ArticleDto;
import de.silvia.backend.api.ListDto;
import de.silvia.backend.models.Article;
import de.silvia.backend.models.ArticleList;
import de.silvia.backend.repositories.IArticleListRepo;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import de.silvia.backend.security.services.UserService;
import de.silvia.backend.services.ArticleListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArtikelListControllerTest {

    private final IArticleListRepo testArtikelListRepo = mock(IArticleListRepo.class);
    private final ArticleListService mockedArticleListService = mock(ArticleListService.class);
    private final IUserRepo testUserRepo = mock(IUserRepo.class);
    private final UserService testUserService = new UserService(testUserRepo);
    private final ArticleListController testArticleListController =
            new ArticleListController(mockedArticleListService, testUserService);

    @Test
    void getAll() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        ArticleList testArtikelList = ArticleList.newArticleList(listId, artikels, userId);
        List<ArticleList> testArtikelListen = List.of(testArtikelList);
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedArticleListService.getAllArticleLists(testUser.getUsername()))
                .thenReturn(testArtikelListen);
        //Then
        assertEquals(testArtikelListen, testArticleListController.getAll(testPrincipal));
    }

    @Test
    void addArtikelList() throws CloneNotSupportedException {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        ListDto testListDto = new ListDto("testListName");

        List<Article> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        ArticleList testArtikelList = ArticleList.newArticleList(listId, artikels, userId);
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedArticleListService.addArticleList(testUser.getUsername(), testListDto.getListName()))
                .thenReturn(testArtikelList);
        //Then
        assertEquals(testArtikelList, testArticleListController.addArticleList(testPrincipal, testListDto));
    }

    @Test
    void deleteList() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        String testListId = "testListId";
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        //Then
        testArticleListController.deleteList(testPrincipal, testListId);
        verify(mockedArticleListService, times(1))
                .deleteArticleList(testUser.getUsername(), testListId);
    }

    @Test
    void addArticle() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        String testListId = "testListId";
        List<Article> artikels = new java.util.ArrayList<>(Collections.emptyList());
        ArticleDto testArtikelDto = new ArticleDto("ArtikelName", 5, "Einheiten");
        Article testArtikel = new Article(testArtikelDto);
        artikels.add(testArtikel);
        ArticleList testArtikelList = ArticleList.newArticleList(testListId, artikels, testUser.getUsername());
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedArticleListService.addArticle(testUser.getUsername(), testListId, testArtikelDto))
                .thenReturn(testArtikelList);
        //Then
        assertTrue(testArticleListController.addArticle(testPrincipal, testArtikelDto, testListId)
                .getListOfArticles().contains(testArtikel));
    }

    @Test
    void delArticle() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        String testListId = "testListId";
        String testArticleName = "testArtikelName";
        List<Article> artikels = Collections.emptyList();
        ArticleList testArtikelList = ArticleList.newArticleList(testListId, artikels, testUser.getUsername());
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(testArtikelListRepo.findArticleListByUserIdAndListId(testUser.getUsername(), testListId))
                .thenReturn(testArtikelList);
        //Then
        testArticleListController.delArticle(testPrincipal, testArticleName, testListId);
        verify(mockedArticleListService, times(1))
                .deleteArticle(testUser.getUsername(), testListId, testArticleName);
    }

    @Test
    void decreaseArticle() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        String testListId = "testListId";
        List<Article> artikels = new java.util.ArrayList<>(Collections.emptyList());
        ArticleDto testArtikelDto = new ArticleDto("ArtikelName", 5, "Einheiten");
        Article testArtikel = new Article(testArtikelDto);
        artikels.add(testArtikel);
        ArticleList testArtikelList = ArticleList.newArticleList(testListId, artikels, testUser.getUsername());
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(testArtikelListRepo.findArticleListByUserIdAndListId(testUser.getUsername(), testListId))
                .thenReturn(testArtikelList);
        //Then
        testArticleListController.decreaseArticle(testPrincipal,"ArtikelName", testListId);
        verify(mockedArticleListService, times(1))
                .decreaseArticle(testUser.getUsername(), testListId, testArtikel.getName());
    }

    @Test
    void increaseArticle() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        String testListId = "testListId";
        List<Article> artikels = new java.util.ArrayList<>(Collections.emptyList());
        ArticleDto testArtikelDto = new ArticleDto("ArtikelName", 5, "Einheiten");
        Article testArtikel = new Article(testArtikelDto);
        artikels.add(testArtikel);
        ArticleList testArtikelList = ArticleList.newArticleList(testListId, artikels, testUser.getUsername());
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(testArtikelListRepo.findArticleListByUserIdAndListId(testUser.getUsername(), testListId))
                .thenReturn(testArtikelList);
        //Then
        testArticleListController.increaseArticle(testPrincipal,"ArtikelName", testListId);
        verify(mockedArticleListService, times(1))
                .increaseArticle(testUser.getUsername(), testListId, testArtikel.getName());
    }
}