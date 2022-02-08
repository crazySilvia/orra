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

    public ArtikelListService(IArtikelListRepo artikelListRepo) {
        this.artikelListRepo = artikelListRepo;
    }

    public ArtikelList addArtikelList(String listname) throws CloneNotSupportedException {
        if(artikelListRepo.findArtikelListByListName(listname).isPresent()){
            throw new CloneNotSupportedException("Liste mit Namen " + listname + " gibt es schon!");
        }
        List<Artikel> artikelList = Collections.emptyList();
        LOG.info("Liste mit Namen " + listname + " hinzugefügt!");
        return artikelListRepo.insert(ArtikelList.newArtikelList(listname, artikelList));
    }

    public List<ArtikelList> getAllArtikelLists() {
        return artikelListRepo.findAllLists();
    }

    public ArtikelList addArtikel(ArtikelDto artikelDto, String listname) {
        final Artikel artikel = Artikel.newArtikel(artikelDto.getName(), artikelDto.getAnzahl());
        Optional<ArtikelList> optionalArtikelList = artikelListRepo.findArtikelListByListName(listname);
        if(optionalArtikelList.isEmpty()) {
            throw new NoSuchElementException("Liste mit Namen " + listname + " nicht gefunden!");
        }
        ArtikelList artikelList = optionalArtikelList.get();
        artikelList.addArticle(artikel);
        return artikelListRepo.save(artikelList);
    }

    public void deleteArtikel(ArtikelDto artikelDto, String listname){
        final String id = artikelDto.getName();
        Optional<ArtikelList> optionalArtikelList = artikelListRepo.findArtikelListByListName(listname);
        if(optionalArtikelList.isEmpty()) {
            throw new NoSuchElementException("Liste mit Namen " + listname + " nicht gefunden!");
        }
        artikelListRepo.deleteById(id);
    }
}
