package de.silvia.backend.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ArtikelTest {

    @Test
    void decreaseArtikel() {
        Article testArtikel = Article.builder().amount(5).name("Testartikel").build();
        testArtikel.decreaseArticle();
        assertEquals(4, testArtikel.getAmount());
    }

    @Test
    void increaseArtikel() {
        Article testArtikel = Article.builder().name("TestArtikel").amount(5).build();
        testArtikel.increaseArticle();
        assertEquals(6, testArtikel.getAmount());
    }
}