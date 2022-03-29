package de.silvia.backend.controller;

import de.silvia.backend.api.ArticleDto;
import de.silvia.backend.api.ListDto;
import de.silvia.backend.models.ArticleList;
import de.silvia.backend.security.models.User;
import de.silvia.backend.security.services.UserService;
import de.silvia.backend.services.ArticleListService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/lists")
public class ArticleListController {

    private static final Log LOG = LogFactory.getLog(ArticleListController.class);
    private final ArticleListService aServ;
    private final UserService userService;

    @Autowired
    public ArticleListController(ArticleListService aServ, UserService userService) {
        this.aServ = aServ;
        this.userService = userService;
    }

    private User getUser(Principal principal) throws ResponseStatusException {
        try {
            return userService.getUserByPrincipal(principal);
        }catch (UsernameNotFoundException e){
            LOG.warn("No User found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No User found");
        }
    }

    @GetMapping
    public List<ArticleList> getAll(Principal principal) {
        User user = getUser(principal);
        return aServ.getAllArticleLists(user.getUsername());
    }

    @PostMapping
    public ArticleList addArticleList(Principal principal, @RequestBody ListDto listDto) throws CloneNotSupportedException {
        User user = getUser(principal);
        return aServ.addArticleList(user.getUsername(), listDto.getListName());
    }

    @DeleteMapping("/{listId}")
    public void deleteList(Principal principal, @PathVariable String listId){
        User user = getUser(principal);
        aServ.deleteArticleList(user.getUsername(), listId);
    }

    @PostMapping("/{listId}")
    public ArticleList addArticle(Principal principal, @RequestBody ArticleDto articleDto, @PathVariable String listId){
        User user = getUser(principal);
        return aServ.addArticle(user.getUsername(), listId, articleDto);
    }

    @DeleteMapping(value = "/{listId}/remove/{articleId}")
    public void delArticle(Principal principal, @PathVariable String articleId, @PathVariable String listId){
        User user = getUser(principal);
        aServ.deleteArticle(user.getUsername(), listId, articleId);
    }

    @PatchMapping("/{listId}/decrease/{articleId}")
    public void decreaseArticle(Principal principal, @PathVariable String articleId, @PathVariable String listId){
        User user = getUser(principal);
        aServ.decreaseArticle(user.getUsername(), listId, articleId);
    }

    @PatchMapping("/{listId}/increase/{articleId}")
    public void increaseArticle(Principal principal, @PathVariable String articleId, @PathVariable String listId){
        User user = getUser(principal);
        aServ.increaseArticle(user.getUsername(), listId, articleId);
    }
}