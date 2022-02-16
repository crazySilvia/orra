package de.silvia.backend.services;

import de.silvia.backend.models.Artikel;
import de.silvia.backend.models.ArtikelList;
import de.silvia.backend.repositories.IArtikelListRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ArtikelListServiceTest {

    private final IArtikelListRepo artikelListRepo = mock(IArtikelListRepo.class);
    private final ArtikelListService artikelListService = new ArtikelListService(artikelListRepo);

    @Test
    void addArtikelList() throws CloneNotSupportedException {
        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listName = "listName";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listName, artikels, userId);

        when(artikelListRepo.insert(testArtikelList))
                .thenReturn(testArtikelList);

        assertEquals(testArtikelList, artikelListService.addArtikelList(userId, listName));
    }

    @Test
    void shouldThrowErrorIfAddArtikelList() {
        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listName = "listName";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listName, artikels, userId);

        when(artikelListRepo.findArtikelListByUserIdAndListName(userId, listName))
                .thenReturn(testArtikelList);

        assertThrows(CloneNotSupportedException.class, () -> artikelListService.addArtikelList(userId, listName));
    }

    @Test
    void deleteArtikelList() {
        String userId = "userId";
        String listName = "listName";

        artikelListService.deleteArtikelList(userId, listName);
        verify(artikelListRepo, times(1)).deleteArtikelListByUserIdAndListName(userId, listName);
    }

    @Test
    void getAllArtikelLists() {
        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listName = "listName";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listName, artikels, userId);
        List<ArtikelList> testArtikelListen = List.of(testArtikelList);

        when(artikelListRepo.findAllByUserId(userId))
                .thenReturn(testArtikelListen);

        assertEquals(testArtikelListen, artikelListService.getAllArtikelLists(userId));
    }

    @Test
    void addArtikel() {
        /*List<Artikel> artikels = new java.util.ArrayList<>(Collections.emptyList());
        String userId = "userId";
        String listName = "listName";
        ArtikelDto testArtikelDto = new ArtikelDto("artikelName", 7);
        Artikel testArtikel = new Artikel(testArtikelDto);
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listName, artikels, userId);
        testArtikelList.addArticle(testArtikel);
        when(artikelListRepo.save(testArtikelList))
                .thenReturn(testArtikelList);

        artikelListService.addArtikel(userId, listName, testArtikelDto);
        verify(artikelListRepo, times(1)).save(testArtikelList);*/
    }

    @Test
    void getArtikelList() {
    }

    @Test
    void deleteArtikel() {
    }

    @Test
    void decreaseArtikel() {
    }

    @Test
    void increaseArtikel() {
    }
}