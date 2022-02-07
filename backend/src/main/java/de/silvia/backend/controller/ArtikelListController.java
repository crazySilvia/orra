package de.silvia.backend.controller;

import de.silvia.backend.api.ArtikelDto;
import de.silvia.backend.models.ArtikelList;
import de.silvia.backend.services.ArtikelListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api")
public class ArtikelListController {

    private final ArtikelListService aServ;

    @Autowired
    public ArtikelListController(ArtikelListService aServ) {
        this.aServ = aServ;
    }

    @GetMapping("artikelList")
    public List<ArtikelList> getAll() {
        return aServ.getAllArtikelLists();
    }

    @PostMapping("artikelList")
    public ArtikelList addArtikelList(@RequestBody String listname) throws CloneNotSupportedException {
        return aServ.addArtikelList(listname);
    }

    @PatchMapping("{listname}")
    public ArtikelList addArticle(@RequestBody ArtikelDto artikelDto, @PathVariable String listname){
        return aServ.addArtikel(artikelDto, listname);
    }

    @DeleteMapping("{listname}")
    public void delArticle(@RequestBody ArtikelDto artikelDto, @PathVariable String listname){
        aServ.deleteArtikel(artikelDto, listname);
    }
}