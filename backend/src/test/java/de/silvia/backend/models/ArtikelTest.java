package de.silvia.backend.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ArtikelTest {

    @Test
    void decreaseArtikel() {
        Artikel testArtikel = Artikel.builder().anzahl(5).name("Testartikel").build();
        testArtikel.decreaseArtikel();
        assertEquals(4, testArtikel.getAnzahl());
    }

    @Test
    void increaseArtikel() {
        Artikel testArtikel = Artikel.builder().name("TestArtikel").anzahl(5).build();
        testArtikel.increaseArtikel();
        assertEquals(6, testArtikel.getAnzahl());
    }
}