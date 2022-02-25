package de.silvia.backend.models;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ArtikelListTest {

    @Test
    void addArticle() {
        List<Artikel> testArtikels =  new java.util.ArrayList<>(Collections.emptyList());
        ArtikelList testArtikelList = ArtikelList.builder().listName("Testlist").listId("123").userId("userid").artikels(testArtikels).build();
        Artikel testArtikel = Artikel.builder().name("TestArtikel").anzahl(7).build();
        testArtikelList.addArticle(testArtikel);
        assertFalse(testArtikelList.getArtikels().isEmpty());
    }
}