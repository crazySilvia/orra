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

    public ArtikelList addArtikelList(String userId, String id) throws CloneNotSupportedException {
        if (artikelListRepo.findArtikelListByUserIdAndId(userId, id) != null) {
            throw new CloneNotSupportedException("Liste mit id " + id + " gibt es schon!");
        }
        List<Artikel> artikelList = Collections.emptyList();
        LOG.info("Liste mit id " + id + " hinzugefügt!");
        return artikelListRepo.insert(ArtikelList.newArtikelList(id, artikelList, userId));
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

    public ArtikelList getArtikelList(String userId, String id) {
        ArtikelList artikelList = artikelListRepo.findArtikelListByUserIdAndId(userId, id);
        if (artikelList == null) {
            throw new NoSuchElementException("Liste mit id " + id + " nicht gefunden!");
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

    public void decreaseArtikel(String userId, String listId, String artikelName) {
        ArtikelList artikelList = getArtikelList(userId, listId);
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