package de.silvia.backend.services;

import de.silvia.backend.api.ArtikelDto;
import de.silvia.backend.models.Artikel;
import de.silvia.backend.models.ArtikelList;
import de.silvia.backend.repositories.IArtikelListRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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

        when(artikelListRepo.findArtikelListByUserIdAndListId(userId, listName))
                .thenReturn(testArtikelList);

        assertThrows(CloneNotSupportedException.class, () -> artikelListService.addArtikelList(userId, listName));
    }

    @Test
    void deleteArtikelList() {
        String userId = "userId";
        String listId = "listId";

        //doThrow(new IllegalArgumentException()).when(artikelListRepo).deleteArtikelListByUserIdAndListId(userId, listId);
        doNothing().when(artikelListRepo).deleteArtikelListByUserIdAndListId(userId, listId);

        artikelListService.deleteArtikelList(userId, listId);
        verify(artikelListRepo, times(1)).deleteArtikelListByUserIdAndListId(userId, listId);
    }

    @Test
    void getAllArtikelLists() {
        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listId, artikels, userId);
        List<ArtikelList> testArtikelListen = List.of(testArtikelList);

        when(artikelListRepo.findAllByUserId(userId))
                .thenReturn(testArtikelListen);

        assertEquals(testArtikelListen, artikelListService.getAllArtikelLists(userId));
    }

    @Test
    void addArtikel() {
        List<Artikel> artikels = new java.util.ArrayList<>(Collections.emptyList());
        String userId = "userId";
        String listId = "listId";
        ArtikelDto testArtikelDto = new ArtikelDto("artikelName", 7);
        Artikel testArtikel = new Artikel(testArtikelDto);
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listId, artikels, userId);
        testArtikelList.addArticle(testArtikel);
        when(artikelListRepo.findArtikelListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);
        when(artikelListRepo.save(testArtikelList))
                .thenReturn(testArtikelList);

        artikelListService.addArtikel(userId, listId, testArtikelDto);
        verify(artikelListRepo, times(1)).save(testArtikelList);
    }

    @Test
    void getArtikelList() {
        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listId, artikels, userId);

        when(artikelListRepo.findArtikelListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);

        assertEquals(testArtikelList, artikelListService.getArtikelList(userId, listId));
    }

    @Test
    void shouldFailIfGetArtikelList(){
        String userId = "userId";
        String listId = "listId";

        when(artikelListRepo.findArtikelListByUserIdAndListId(userId, listId))
                .thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> artikelListService.getArtikelList(userId, listId));
    }

    @Test
    void deleteArtikel() {
        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        String artikelName = "artikelName";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listId, artikels, userId);

        when(artikelListRepo.findArtikelListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);

        artikelListService.deleteArtikel(userId, listId, artikelName);
        verify(artikelListRepo, times(1)).save(testArtikelList);
    }

    @Test
    void decreaseArtikel() {
        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        String artikelName = "artikelName";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listId, artikels, userId);

        when(artikelListRepo.findArtikelListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);

        artikelListService.decreaseArtikel(userId, listId, artikelName);
        verify(artikelListRepo, times(1)).save(testArtikelList);
    }

    @Test
    void increaseArtikel() {
        List<Artikel> artikels = Collections.emptyList();
        String userId = "userId";
        String listId = "listId";
        String artikelName = "artikelName";
        ArtikelList testArtikelList = ArtikelList.newArtikelList(listId, artikels, userId);

        when(artikelListRepo.findArtikelListByUserIdAndListId(userId, listId))
                .thenReturn(testArtikelList);

        artikelListService.increaseArtikel(userId, listId, artikelName);
        verify(artikelListRepo, times(1)).save(testArtikelList);
    }
}