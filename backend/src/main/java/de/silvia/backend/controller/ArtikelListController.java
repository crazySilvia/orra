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

    @DeleteMapping("/{listId}")
    public void deleteList(@PathVariable String listId){
        aServ.deleteArtikelList(listId);
    }

    @PostMapping("/{listId}")
    public ArtikelList addArticle(@RequestBody ArtikelDto artikelDto, @PathVariable String listId){
        return aServ.addArtikel(listId, artikelDto);
    }

    @DeleteMapping(value = "/{listId}/remove/{artikelName}")
    public void delArticle(@PathVariable String artikelName, @PathVariable String listId){
        aServ.deleteArtikel(listId, artikelName);
    }

    @PatchMapping("/{listId}/decrease/{artikelName}")
    public void decreaseArticle(@PathVariable String artikelName, @PathVariable String listId){
        aServ.decreaseArtikel(listId, artikelName);
    }

    @PatchMapping("/{listId}/increase/{artikelName}")
    public void increaseArticle(@PathVariable String artikelName, @PathVariable String listId){
        aServ.increaseArtikel(listId, artikelName);
    }
}