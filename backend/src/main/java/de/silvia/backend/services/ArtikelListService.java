package de.silvia.backend.services;

import de.silvia.backend.api.ArtikelDto;
import de.silvia.backend.models.Artikel;
import de.silvia.backend.models.ArtikelList;
import de.silvia.backend.repositories.IArtikelListRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ArtikelListService {

    private final IArtikelListRepo artikelListRepo;
    private static final Log LOG = LogFactory.getLog(ArtikelListService.class);

    public ArtikelListService(IArtikelListRepo artikelListRepo) {
        this.artikelListRepo = artikelListRepo;
    }

    public ArtikelList addArtikelList(String userId, String listName) throws CloneNotSupportedException {
        if (artikelListRepo.findArtikelListByUserIdAndListName(userId, listName) != null) {
            throw new CloneNotSupportedException("Liste mit Namen " + listName + " gibt es schon!");
        }
        List<Artikel> artikels = Collections.emptyList();
        LOG.info("Liste mit Namen " + listName + " hinzugefügt!");
        return artikelListRepo.insert(ArtikelList.newArtikelList(listName, artikels, userId));
    }

    public void deleteArtikelList(String userId, String listName) {
        artikelListRepo.deleteArtikelListByUserIdAndListName(userId, listName);
    }

    public List<ArtikelList> getAllArtikelLists(String userId) {
        return artikelListRepo.findAllByUserId(userId);
    }

    public ArtikelList addArtikel(String userId, String listname, ArtikelDto artikelDto) {
        final Artikel artikel = new Artikel(artikelDto);
        ArtikelList artikelList = getArtikelList(userId, listname);
        artikelList.addArticle(artikel);
        return artikelListRepo.save(artikelList);
    }

    public ArtikelList getArtikelList(String userId, String listName) {
        ArtikelList artikelList = artikelListRepo.findArtikelListByUserIdAndListName(userId, listName);
        if (artikelList == null) {
            throw new NoSuchElementException("Liste mit Namen " + listName + " nicht gefunden!");
        }
        return artikelList;
    }

    public void deleteArtikel(String userId, String listName, String artikelName) {
        ArtikelList artikelList = getArtikelList(userId, listName);
        List<Artikel> updateArtikel = artikelList.getArtikels()
                .stream()
                .filter((artikel) -> (!artikel.getName().equals(artikelName)))
                .toList();
        artikelList.setArtikels(updateArtikel);
        artikelListRepo.save(artikelList);
    }

    public void decreaseArtikel(String userId, String listName, String artikelName) {
        ArtikelList artikelList = getArtikelList(userId, listName);
        List<Artikel> artList = artikelList.getArtikels()
                .stream()
                .map((artikel) -> (artikel.getName().equals(artikelName)) ? artikel.decreaseArtikel() : artikel)
                .toList();
        artikelList.setArtikels(artList);
        artikelListRepo.save(artikelList);
    }

    public void increaseArtikel(String userId, String listId, String artikelName) {
        ArtikelList artikelList = getArtikelList(userId, listId);
        List<Artikel> artList = artikelList.getArtikels()
                .stream()
                .map((artikel) -> (artikel.getName().equals(artikelName)) ? artikel.increaseArtikel() : artikel)
                .toList();
        artikelList.setArtikels(artList);
        artikelListRepo.save(artikelList);
    }
}