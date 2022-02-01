import React, {ChangeEvent, FormEvent, useContext, useState} from "react";
import Header from "../Components/Header";
import NavBar from "../Components/NavBar";
import {AuthContext} from "../Context/AuthProvider";
import {useNavigate} from "react-router-dom";
import {register} from "../Services/apiService";
import {NewUserDto} from "../Api/NewUserDto";


export default function RegisterPage(){

    const[userName, setUserName] = useState<string>()
    const [password, setPassword] = useState<string>()
    const[firstName, setFirstName] = useState<string>()
    const[lastName, setLastName] = useState<string>()
    const[email, setEmail] = useState<string>()

    const navigate = useNavigate()
    const {setJwt} = useContext(AuthContext)

    const onFirstNameChange = (event: ChangeEvent<HTMLInputElement>) => {
        setFirstName(event.target.value)
    }

    const onLastNameChange = (event: ChangeEvent<HTMLInputElement>) => {
        setLastName(event.target.value)
    }

    const onUserNameChange = (event: ChangeEvent<HTMLInputElement>) => {
        setUserName(event.target.value)
    }

    const onPasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value)
    }

    const onEmailChange = (event: ChangeEvent<HTMLInputElement>) => {
        setEmail(event.target.value)
    }


    //ToDo Daten in ein UserDto packen und ans Backend schicken

    const  newUserDto = {firstName, lastName, userName, password, email} as NewUserDto;

    const onSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        register({newUserDto}).then((data: string) => {
            setJwt(data)
            navigate('/')
        })
            .catch(() => console.error("Error"))
    }


    return(
        <div className="registerpage">
        <NavBar />
        <Header title="Account anlegen"/>

        <form onSubmit={onSubmit}>
            <input type="text" placeholder="Vorname" onChange={onFirstNameChange} value={firstName}/>
            <input type="text" placeholder="Nachname" onChange={onLastNameChange} value={lastName}/>
            <input type="email" placeholder="E-Mail" onChange={onEmailChange} value={email}/>
            <input type="text" placeholder="Nickname" onChange={onUserNameChange} value={userName}/>
            <input type="password" placeholder="Passwort" onChange={onPasswordChange} value={password}/>
            <button type="submit">Registrieren</button>
        </form>
        </div>
)
}