package de.silvia.backend.services;

import de.silvia.backend.api.ArtikelDto;
import de.silvia.backend.models.Artikel;
import de.silvia.backend.models.ArtikelList;
import de.silvia.backend.repositories.IArtikelListRepo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArtikelListService {

    private final IArtikelListRepo artikelListRepo;
    private static final Log LOG = LogFactory.getLog(ArtikelListService.class);

    public ArtikelListService(IArtikelListRepo artikelListRepo)
    {
        this.artikelListRepo = artikelListRepo;
    }

    public ArtikelList addArtikelList(String id) throws CloneNotSupportedException {
        if(artikelListRepo.findArtikelListById(id).isPresent()){
            throw new CloneNotSupportedException("Liste mit id " + id + " gibt es schon!");
        }
        List<Artikel> artikelList = Collections.emptyList();
        LOG.info("Liste mit id " + id + " hinzugefügt!");
        return artikelListRepo.insert(ArtikelList.newArtikelList(id, artikelList));
    }

    public void deleteArtikelList(String listName){
        artikelListRepo.deleteById(listName);
    }

    public List<ArtikelList> getAllArtikelLists() {
        return artikelListRepo.findAll();
    }

    public ArtikelList addArtikel(String listname, ArtikelDto artikelDto) {
        final Artikel artikel = new Artikel(artikelDto);
        ArtikelList artikelList = getArtikelList(listname);
        artikelList.addArticle(artikel);
        return artikelListRepo.save(artikelList);
    }

    public ArtikelList getArtikelList(String id){
        Optional<ArtikelList> optionalArtikelList = artikelListRepo.findArtikelListById(id);
        if(optionalArtikelList.isEmpty()) {
            throw new NoSuchElementException("Liste mit id " + id + " nicht gefunden!");
        }
        return optionalArtikelList.get();
    }

    public void deleteArtikel(String listName, String artikelName){
        ArtikelList artikelList = getArtikelList(listName);
        List<Artikel> updateArtikel = artikelList.getArtikels()
                .stream()
                .filter((artikel) -> (!artikel.getName().equals(artikelName)))
                .toList();
        artikelList.setArtikels(updateArtikel);
        artikelListRepo.save(artikelList);
    }

    public void decreaseArtikel(String listId, String artikelName){
        ArtikelList artikelList = getArtikelList(listId);
        List<Artikel> artList = artikelList.getArtikels()
                .stream()
                .map((artikel) -> (artikel.getName().equals(artikelName)) ? artikel.decreaseArtikel():artikel)
                .toList();
        artikelList.setArtikels(artList);
        artikelListRepo.save(artikelList);
    }

    public void increaseArtikel(String listId, String artikelName){
        ArtikelList artikelList = getArtikelList(listId);
        List<Artikel> artList = artikelList.getArtikels()
                .stream()
                .map((artikel) -> (artikel.getName().equals(artikelName)) ? artikel.increaseArtikel():artikel)
                .toList();
        artikelList.setArtikels(artList);
        artikelListRepo.save(artikelList);
    }
}