import React, {ChangeEvent, FormEvent, useContext, useState} from "react";
import {AuthContext} from "../Context/AuthProvider";
import {useNavigate} from "react-router-dom";
import {DataContext} from "../Context/DataProvider";
import {NewCategoryDto} from "../Api/NewCategoryDto";
import {saveNewCategory} from "../Services/apiService";

export default function AddCategoryField(){
    const [input, setInput] = useState<string>("")
    const {token} = useContext(AuthContext)
    const navigate = useNavigate()
    const {setAllCategory, allCategory} = useContext(DataContext)

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setInput(event.target.value)
    }

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        //leert Eingabefeld
        setInput("")
        event.preventDefault()
        const newCategoryDto: NewCategoryDto = {
            categoryName: input
        }
        saveNewCategory(newCategoryDto, token)
            .then((response) => {
                setAllCategory([...allCategory, response])
                return response
            })
            .then((response) => {
                navigate('/' + response.categoryId)
            })
            .catch((er) => console.error(er))
    }

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Kategoriename" onChange={handleChange} value={input} required/>
            <button type={"submit"}>Kategorie hinzufügen</button>
        </form>
    )
}