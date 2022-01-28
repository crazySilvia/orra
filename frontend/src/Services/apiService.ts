import axios, {AxiosResponse} from "axios";

/**
 * Hier passiert der Login
 * @param userInput Login-Daten der User
 */
export const login = (userInput: { password: string | undefined; name: string | undefined }) =>
    axios.post("/auth/login", userInput)
        .then((response: AxiosResponse<string>) => response.data)