import React from 'react';
import {ReactElement, useContext} from "react";
import {Navigate} from "react-router-dom";
import {AuthContext} from "../Context/AuthProvider";


export default function RequireAuth({children} : {children: ReactElement<any, any>}) {
    const {token} = useContext(AuthContext)
    // das sorgt dafür. dass der User immer zum Login geführt wird, wenn er noch nicht eingeloggt ist
    return token ? children : <Navigate to="/login"/>
}