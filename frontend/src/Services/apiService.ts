import axios, {AxiosResponse} from "axios";
import {NewUserDto} from "../Api/NewUserDto";
import {NewListDto} from "../Api/NewListDto";

export const deleteArticle = (listName: string, artikelName: string, token?: string) =>
    axios.delete(`/api/lists/${listName}/remove/${artikelName}`, token ? {headers: {"Authorization": "Bearer " + token}} : {})
        .then(response => response.data)
/**
 * Hier passiert das Löschen der Liste
 * @param listName name der jeweiligen Liste
 * @param token usertoken
 */
export const deleteList = (listName: string, token?: string) =>
    axios.delete(`/api/lists/${listName}`, token ? {headers: {"Authorization": "Bearer " + token}} : {})
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