package de.silvia.backend.controller;

import de.silvia.backend.api.ArtikelDto;
import de.silvia.backend.api.ListDto;
import de.silvia.backend.models.ArtikelList;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.services.UserService;
import de.silvia.backend.services.ArtikelListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/lists")
public class ArtikelListController {

    private final ArtikelListService aServ;
    private final UserService userService;

    @Autowired
    public ArtikelListController(ArtikelListService aServ, UserService userService) {
        this.aServ = aServ;
        this.userService = userService;
    }

    private User getUser(Principal principal) throws ResponseStatusException {
        try {
            return userService.getUserByPrincipal(principal);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No User found");
        }
    }

    @GetMapping
    public List<ArtikelList> getAll(Principal principal) {
        User user = getUser(principal);
        return aServ.getAllArtikelLists(user.getUsername());
    }

    @PostMapping
    public ArtikelList addArtikelList(Principal principal, @RequestBody ListDto listDto) throws CloneNotSupportedException {
        User user = getUser(principal);
        return aServ.addArtikelList(user.getUsername(), listDto.getListName());
    }

    @DeleteMapping("/{listId}")
    public void deleteList(Principal principal, @PathVariable String listId) {
        User user = getUser(principal);
        aServ.deleteArtikelList(user.getUsername(), listId);
    }

    @PostMapping("/{listId}")
    public ArtikelList addArticle(Principal principal, @RequestBody ArtikelDto artikelDto, @PathVariable String listId) {
        User user = getUser(principal);
        return aServ.addArtikel(user.getUsername(), listId, artikelDto);
    }

    @DeleteMapping(value = "/{listId}/remove/{artikelName}")
    public void delArticle(Principal principal, @PathVariable String artikelName, @PathVariable String listId) {
        User user = getUser(principal);
        aServ.deleteArtikel(user.getUsername(), listId, artikelName);
    }

    @PatchMapping("/{listId}/decrease/{artikelName}")
    public void decreaseArticle(Principal principal, @PathVariable String artikelName, @PathVariable String listId) {
        User user = getUser(principal);
        aServ.decreaseArtikel(user.getUsername(), listId, artikelName);
    }

    @PatchMapping("/{listId}/increase/{artikelName}")
    public void increaseArticle(Principal principal, @PathVariable String artikelName, @PathVariable String listId) {
        User user = getUser(principal);
        aServ.increaseArtikel(user.getUsername(), listId, artikelName);
    }
}