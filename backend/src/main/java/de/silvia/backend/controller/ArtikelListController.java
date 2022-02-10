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

    @PatchMapping("/{listname}")
    public ArtikelList addArticle(@RequestBody ArtikelDto artikelDto, @PathVariable String listname){
        return aServ.addArtikel(artikelDto, listname);
    }

    @DeleteMapping(value = "/{listname}/remove/{artikelName}")
    public void delArticle(@PathVariable String artikelName, @PathVariable String listname){
        aServ.deleteArtikel(listname, artikelName);
    }
}