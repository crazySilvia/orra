package de.silvia.backend.controller;

import de.silvia.backend.api.ArtikelDto;
import de.silvia.backend.api.ListDto;
import de.silvia.backend.models.ArtikelList;
import de.silvia.backend.services.ArtikelListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/lists")
public class ArtikelListController {

    private final ArtikelListService aServ;

    @Autowired
    public ArtikelListController(ArtikelListService aServ) {
        this.aServ = aServ;
    }

    @GetMapping
    public List<ArtikelList> getAll() {
        return aServ.getAllArtikelLists();
    }

    @PostMapping
    public ArtikelList addArtikelList(@RequestBody ListDto listDto) throws CloneNotSupportedException {
        return aServ.addArtikelList(listDto.getListName());
    }

    @DeleteMapping("/{listName}")
    public void deleteList(@PathVariable String listName){
        aServ.deleteArtikelList(listName);
    }

    @PostMapping("/{listName}")
    public ArtikelList addArticle(@RequestBody ArtikelDto artikelDto, @PathVariable String listName){
        return aServ.addArtikel(listName, artikelDto);
    }

    @DeleteMapping(value = "/{listName}/remove/{artikelName}")
    public void delArticle(@PathVariable String artikelName, @PathVariable String listName){
        aServ.deleteArtikel(listName, artikelName);
    }

    @PatchMapping("/{listName}/decrease/{artikelName}")
    public void decreaseArticle(@PathVariable String artikelName, @PathVariable String listName){
        aServ.decreaseArtikel(listName, artikelName);
    }

    @PatchMapping("/{listName}/increase/{artikelName}")
    public void increaseArticle(@PathVariable String artikelName, @PathVariable String listName){
        aServ.increaseArtikel(listName, artikelName);
    }
}