import React, {ChangeEvent, FormEvent, useContext, useState} from "react";
import Header from "../Components/Header";
import NavBar from "../Components/NavBar";
import {AuthContext} from "../Context/AuthProvider";
import {useNavigate} from "react-router-dom";
import {login} from "../Services/apiService";

export default function LoginPage(){

    const[name, setName] = useState<string>()
    const [password, setPassword] = useState<string>()

    const navigate = useNavigate()

    const {setJwt} = useContext(AuthContext)

    const onUserNameChange = (event: ChangeEvent<HTMLInputElement>) => {
        setName(event.target.value)
    }

    const onPasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value)
    }

    const onSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        login({name, password})
            .then((data : string) => {
                setJwt(data)
                navigate('/vorrat')
            })
            .catch(() => console.error("Error"))
    }

    return(
        <div className="loginpage">
            <NavBar />
            <Header title="Anmelden"/>

            <form onSubmit={onSubmit}>
                <input type="text" placeholder="Nutzername" onChange={onUserNameChange} value={name}/>
                <input type="password" placeholder="Passwort" onChange={onPasswordChange} value={password}/>
                <button type="submit">Login</button>
            </form>
        </div>
    )
}