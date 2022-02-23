package de.silvia.backend.controller;

import de.silvia.backend.api.ArtikelDto;
import de.silvia.backend.api.ListDto;
import de.silvia.backend.models.Artikel;
import de.silvia.backend.models.ArtikelList;
import de.silvia.backend.repositories.IArtikelListRepo;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.repositories.IUserRepo;
import de.silvia.backend.security.services.UserService;
import de.silvia.backend.services.ArtikelListService;
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

    private final IArtikelListRepo testArtikelListRepo = mock(IArtikelListRepo.class);
    private final ArtikelListService mockedArtikelListService = mock(ArtikelListService.class);
    private final IUserRepo testUserRepo = mock(IUserRepo.class);
    private final UserService testUserService = new UserService(testUserRepo);
    private final ArtikelListController testArtikelListController =
            new ArtikelListController(mockedArtikelListService, testUserService);

    @Test
    void getAll() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listId, artikels, userId);
        List<ArtikelList> testArtikelListen = List.of(testArtikelList);
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedArtikelListService.getAllArtikelLists(testUser.getUsername()))
                .thenReturn(testArtikelListen);
        //Then
        assertEquals(testArtikelListen, testArtikelListController.getAll(testPrincipal));
    }

    @Test
    void addArtikelList() throws CloneNotSupportedException {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));

        ListDto testListDto = new ListDto("testListName");

        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listId, artikels, userId);
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedArtikelListService.addArtikelList(testUser.getUsername(), testListDto.getListName()))
                .thenReturn(testArtikelList);
        //Then
        assertEquals(testArtikelList, testArtikelListController.addArtikelList(testPrincipal, testListDto));
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
        testArtikelListController.deleteList(testPrincipal, testListId);
        verify(mockedArtikelListService, times(1))
                .deleteArtikelList(testUser.getUsername(), testListId);
    }

    @Test
    void addArticle() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        String testListId = "testListId";
        List<Artikel> artikels = new java.util.ArrayList<>(Collections.emptyList());
        ArtikelDto testArtikelDto = new ArtikelDto("ArtikelName", 5);
        Artikel testArtikel = new Artikel(testArtikelDto);
        artikels.add(testArtikel);
        ArtikelList testArtikelList = ArtikelList.newArtikelList(testListId, artikels, testUser.getUsername());
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(mockedArtikelListService.addArtikel(testUser.getUsername(), testListId, testArtikelDto))
                .thenReturn(testArtikelList);
        //Then
        assertTrue(testArtikelListController.addArticle(testPrincipal, testArtikelDto, testListId)
                .getArtikels().contains(testArtikel));
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
        List<Artikel> artikels = Collections.emptyList();
        ArtikelList testArtikelList = ArtikelList.newArtikelList(testListId, artikels, testUser.getUsername());
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(testArtikelListRepo.findArtikelListByUserIdAndListId(testUser.getUsername(), testListId))
                .thenReturn(testArtikelList);
        //Then
        testArtikelListController.delArticle(testPrincipal, testArticleName, testListId);
        verify(mockedArtikelListService, times(1))
                .deleteArtikel(testUser.getUsername(), testListId, testArticleName);
    }

    @Test
    void decreaseArticle() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        String testListId = "testListId";
        List<Artikel> artikels = new java.util.ArrayList<>(Collections.emptyList());
        ArtikelDto testArtikelDto = new ArtikelDto("ArtikelName", 5);
        Artikel testArtikel = new Artikel(testArtikelDto);
        artikels.add(testArtikel);
        ArtikelList testArtikelList = ArtikelList.newArtikelList(testListId, artikels, testUser.getUsername());
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(testArtikelListRepo.findArtikelListByUserIdAndListId(testUser.getUsername(), testListId))
                .thenReturn(testArtikelList);
        //Then
        testArtikelListController.decreaseArticle(testPrincipal,"ArtikelName", testListId);
        verify(mockedArtikelListService, times(1))
                .decreaseArtikel(testUser.getUsername(), testListId, testArtikel.getName());
    }

    @Test
    void increaseArticle() {
        //Given
        Principal testPrincipal = () -> "Susi";
        User testUser = User.newUser("Susi", "su@si.cd", "Schulz",
                "suesue", "su123", List.of(
                        new SimpleGrantedAuthority("API_READWRITE")));
        String testListId = "testListId";
        List<Artikel> artikels = new java.util.ArrayList<>(Collections.emptyList());
        ArtikelDto testArtikelDto = new ArtikelDto("ArtikelName", 5);
        Artikel testArtikel = new Artikel(testArtikelDto);
        artikels.add(testArtikel);
        ArtikelList testArtikelList = ArtikelList.newArtikelList(testListId, artikels, testUser.getUsername());
        //When
        when(testUserRepo.findUserByUsername("Susi")).thenReturn(testUser);
        when(testArtikelListRepo.findArtikelListByUserIdAndListId(testUser.getUsername(), testListId))
                .thenReturn(testArtikelList);
        //Then
        testArtikelListController.increaseArticle(testPrincipal,"ArtikelName", testListId);
        verify(mockedArtikelListService, times(1))
                .increaseArtikel(testUser.getUsername(), testListId, testArtikel.getName());
    }
}