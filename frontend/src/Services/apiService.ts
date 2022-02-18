import axios, {AxiosResponse} from "axios";
import {NewUserDto} from "../Api/NewUserDto";
import {NewListDto} from "../Api/NewListDto";
import {ArticleDto} from "../Api/ArticleDto";

/**
 * Hier passiert das Löschen der Zutaten
 * @param ingredientListId Name der jeweiligen Liste
 * @param ingredientName Name des zu löschenden Artikel
 * @param token usertoken
 */
export const deleteIngredient = (ingredientListId: string, ingredientName: string, token?: string) =>
    axios.delete(`/api/recipes/${ingredientListId}/remove/${ingredientName}`,token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier werden die Rezepte des User abgerufen
 * @param token usertoken
 */
export const recipes = (token?: string) =>
    axios.get("/api/recipes", token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

export const saveNewArticle = (listId: string, articleDto: ArticleDto, token?: string) =>
    axios.post(`/api/lists/${listId}`, articleDto, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Erhöhen der Artikelanzahl
 * @param listId Name der jeweiligen Liste
 * @param artikelName Name des betreffenden Artikel
 * @param token usertoken
 */
export const decreaseArticle = (listId: string, artikelName: string, token?: string) =>
    axios.patch(`/api/lists/${listId}/decrease/${artikelName}`, artikelName, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Senken der Artikelanzahl
 * @param listId Name der jeweiligen Liste
 * @param artikelName Name des betreffenden Artikel
 * @param token usertoken
 */
export const increaseArticle = (listId: string, artikelName: string, token?: string) =>
    axios.patch(`/api/lists/${listId}/increase/${artikelName}`, artikelName, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Löschen der Artikel
 * @param listId Name der jeweiligen Liste
 * @param artikelName Name des zu löschenden Artikel
 * @param token usertoken
 */
export const deleteArticle = (listId: string, artikelName: string, token?: string) =>
    axios.delete(`/api/lists/${listId}/remove/${artikelName}`,token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Löschen der Liste
 * @param listId Name der jeweiligen Liste
 * @param token usertoken
 */
export const deleteList = (listId: string, token?: string) =>
    axios.delete(`/api/lists/${listId}`, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Speichern einer neuen Liste
 * @param newListDto jeweiligen Liste
 * @param token usertoken
 */
export const saveNewList = (newListDto: NewListDto, token?: string) => {
    console.log(newListDto.listName)
    return axios.post("/api/lists", newListDto, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)
}

/**
 * Hier werden die Listen des User abgerufen
 * @param token usertoken
 */
export const listnames = (token?: string) =>
    axios.get("/api/lists", token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert der Login
 * @param userInput Login-Daten der User
 */
export const login = (userInput: { password: string | undefined; name: string | undefined }) =>
    axios.post("/auth/login", userInput)
        .then((response: AxiosResponse<string>) => response.data)

/**
 * Hier passiert das Registrieren
 * @param userInput Login-Daten der User
 */
export const register = (userInput: NewUserDto) =>
    axios.post("/api/user/register", userInput)
        .then((response: AxiosResponse<string>) => response.data)