import React, {ChangeEvent, FormEvent, useContext, useState} from 'react';
import {saveNewList} from "../Services/apiService";
import {AuthContext} from "../Context/AuthProvider";
import {NewListDto} from "../Api/NewListDto";
import {useNavigate} from "react-router-dom";
import {DataContext} from "../Context/DataProvider";

export default function AddListField() {
    const [input, setInput] = useState<string>("")
    const {token} = useContext(AuthContext)
    const navigate = useNavigate()
    const {setAllList, allList} = useContext(DataContext)

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setInput(event.target.value)
    }

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        //leert Eingabefeld:
        setInput("")
        event.preventDefault()
        const newListDto: NewListDto = {
            listName: input
        }
        saveNewList(newListDto, token)
            .then((response) => {
                setAllList([...allList, response])
                return response
            })
            .then((response) => {
                navigate('/' + response.listId)
            })
            .catch((er) => console.error(er))
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="addList_input">
                <input type="text" placeholder="Listenname" onChange={handleChange} value={input}/>
            </div>
            <div className="addList_button">
                <button type={"submit"}>Liste hinzufügen</button>
            </div>
        </form>
    )
}