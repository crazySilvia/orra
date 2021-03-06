import React from 'react';
import {createContext, useState} from "react";
import jwt_decode from 'jwt-decode'

export interface AuthContextType {
    token?: string,
    jwtDecoded?: { sub?: string }
    setJwt: (jwt: string) => void
}

export const AuthContext = createContext<AuthContextType>({
    setJwt: () => {}
})

export default function AuthProvider({children} : {children: React.ReactNode}) {

    const [token, setToken] = useState<string>()
    const [jwtDecoded, setJwtDecoded] = useState({})

    const setJwt = (jwt: string) => {
        setToken(jwt)
        setJwtDecoded(jwt_decode(jwt.toString()))
    }

    return (
        <AuthContext.Provider value={{token, jwtDecoded, setJwt}}>
            {children}
        </AuthContext.Provider>
    )
}