import axios, {AxiosResponse} from "axios";
import {NewUserDto} from "../Api/NewUserDto";

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