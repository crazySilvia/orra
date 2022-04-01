import axios, {AxiosResponse} from "axios";
import {NewUserDto} from "../Api/NewUserDto";
import {NewListDto} from "../Api/NewListDto";
import {ArticleDto} from "../Api/ArticleDto";
import {NewCategoryDto} from "../Api/NewCategoryDto";

/**
 * Hier wird ein neuer Artikel gespeichert
 * @param listId Liste, auf die der Artikel kommt
 * @param articleDto Artikel, der gespeichert wird
 * @param token usertoken
 */
export const saveNewArticle = (listId: string, articleDto: ArticleDto, token?: string) =>
    axios.post(`/api/lists/${listId}`, articleDto, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Erhöhen der Artikelanzahl
 * @param listId ID der jeweiligen Liste
 * @param articleId ID des betreffenden Artikel
 * @param token usertoken
 */
export const decreaseArticle = (listId: string, articleId: string, token?: string) =>
    axios.patch(`/api/lists/${listId}/decrease/${articleId}`, articleId, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Senken der Artikelanzahl
 * @param listId ID der jeweiligen Liste
 * @param articleId ID des betreffenden Artikel
 * @param token usertoken
 */
export const increaseArticle = (listId: string, articleId: string, token?: string) =>
    axios.patch(`/api/lists/${listId}/increase/${articleId}`, articleId, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Löschen der Artikel
 * @param listId ID der jeweiligen Liste
 * @param articleId ID des zu löschenden Artikel
 * @param token usertoken
 */
export const deleteArticle = (listId: string, articleId: string, token?: string) =>
    axios.delete(`/api/lists/${listId}/remove/${articleId}`,token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Löschen der Liste
 * @param listId ID der jeweiligen Liste
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
 * Hier passiert das Löschen der Kategorie
 * @param categoryId ID der jeweiligen Kategorie
 * @param token usertoken
 */
export const deleteCategory = (categoryId: string, token?: string) =>
    axios.delete(`/api/category/${categoryId}`, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)

/**
 * Hier passiert das Speichern einer neuen Kategorie
 * @param newCategoryDto jeweiligen Liste
 * @param token usertoken
 */
export const saveNewCategory = (newCategoryDto: NewCategoryDto, token?: string) => {
    console.log(newCategoryDto.categoryName)
    return axios.post("/api/category", newCategoryDto, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)
}

/**
 * Hier werden die Kategorien der User abgerufen
 * @param token usertoken
 */
export const category = (token?: string) =>
    axios.get("/api/category", token? {headers: {"Authorization": "Bearer " + token}} : {})
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