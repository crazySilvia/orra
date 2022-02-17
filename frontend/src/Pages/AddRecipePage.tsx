
import React, {ChangeEvent, FormEvent, useContext, useState} from 'react';
import {AuthContext} from "../Context/AuthProvider";
import {useNavigate} from "react-router-dom";
import {NewRecipeDto} from "../Api/NewRecipeDto";

export default function AddRecipePage(){

    const[recipeName, setRecipeName] = useState<string>()
    const[ingredientList, setIngredientList] = useState<string[]>()
    const[recipeInstruction, setRecipeInstruction] = useState<string>()

    const {token} = useContext(AuthContext)
    const navigate = useNavigate()

    const onRecipeNameChange = (event: ChangeEvent<HTMLInputElement>) => {
        setRecipeName(event.target.value)
    }

    const onIngredientListChange = (event: ChangeEvent<HTMLInputElement>) => {
        setIngredientList()
    }

    const onRecipeInstructionChange = (event: ChangeEvent<HTMLInputElement>) => {
        setRecipeInstruction(event.target.value)
    }

    const newRecipeDto = {recipeName, ingredientList, recipeInstruction} as NewRecipeDto;

    const onSubmit = (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        addRecipe(newRecipeDto).then(() => {
            navigate('/')
        })
            .catch((er) => console.error(er))
    }

    /*const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const newRecipeDto: NewRecipeDto = {
            recipeName: recipeName,
            ingredientList: input,
            recipeInstruction: input
        }
    }*/

    return (
        <form onSubmit={onSubmit}>
        <div>
            <input type="text" placeholder="Rezeptname" onChange={onRecipeNameChange} value={recipeName}/>
    <input type="[]" placeholder="Zutaten hier rein schreiben" onChange={onIngredientListChange} value={ingredientList}/>
    <textarea placeholder="Rezeptanleitung hier rein schreiben." onChange={onRecipeInstructionChange} value={recipeInstruction}/>
    </div>
    <div>
    <button type={"submit"}>Rezept hinzufügen</button>
    </div>
    </form>
)
}