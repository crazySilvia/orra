import React, {ChangeEvent, FormEvent, useContext, useState} from 'react';
import {AuthContext} from "../Context/AuthProvider";
import {useNavigate} from "react-router-dom";
import Header from "../Components/Header";
import {IIngredient} from "../Model/Ingredient";
import IngredientComponent from "../Components/IngredientComponent";
import {DataContext} from "../Context/DataProvider";
import RecipeSidebar from "../Components/RecipeSidebar";
import {IngredientDto} from "../Api/IngredientDto";
import {saveNewIngredient} from "../Services/apiService";

export default function AddRecipePage() {

    const {allRecipe, refresh} = useContext(DataContext)
    const [recipeName, setRecipeName] = useState<string>()
    const [ingredientName, setIngredientName] = useState<string>()
    const [amount, setAmount] = useState<string>()
    const [recipeInstruction, setRecipeInstruction] = useState<string>()


    const {token} = useContext(AuthContext)
    const navigate = useNavigate()




    const onRecipeNameChange = (event: ChangeEvent<HTMLInputElement>) => {
        setRecipeName(event.target.value)
    }

    const handleChangeName = (event: ChangeEvent<HTMLInputElement>) => {
        setIngredientName(event.target.value)
    }

    const handleChangeAmount = (event: ChangeEvent<HTMLInputElement>) => {
        setAmount(event.target.value)
    }

    //handleSubmitIngredient


    const handleSubmitIngredient = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const ingredientDto: IngredientDto = {
            ingredientName: ingredientName,
            ingredientAmount: amount
        }
        saveNewIngredient(recipeName, ingredientDto, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }


    /*const onIngredientListChange = (event: ChangeEvent<HTMLInputElement>) => {
        setIngredientList()
    }*/

    const onRecipeInstructionChange = (event: ChangeEvent<HTMLInputElement>) => {
        setRecipeInstruction(event.target.value)
    }

/*
        const newRecipeDto = {recipeName, ingredientList, recipeInstruction} as NewRecipeDto;

        const onSubmit = (event: FormEvent<HTMLFormElement>) => {
            event.preventDefault()
            addRecipe(newRecipeDto).then(() => {
                navigate('/')
            })
                .catch((er) => console.error(er))
        }*/


    /*const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const newRecipeDto: NewRecipeDto = {
            recipeName: recipeName,
            ingredientList: input,
            recipeInstruction: input
        }
    }*/

    return (
        <body>
        <Header title={"Rezept hinzufügen"}/>
        <RecipeSidebar lists={allRecipe}/>
        <form onSubmit={handleSubmit}>
                <input type="text" placeholder="Rezeptname" onChange={onRecipeNameChange} value={recipeName}/>

            <form onSubmit={handleSubmitIngredient}>
                <div className="addIngredient_input">
                    <input type="text" placeholder="Zutat" onChange={handleChangeName} value={ingredientName}/>
                    <input type="text" placeholder="Menge" onChange={handleChangeAmount} value={amount}/>
                </div>
                <div className="addIngredient_button">
                    <button type={"submit"}>Zutat hinzufügen</button>
                </div>
            </form>

                <textarea placeholder="Rezeptanleitung hier rein schreiben."
                          onChange={handleChangeInstruction} value={recipeInstruction}/>

            <div>
                <button type={"submit"}>Rezept hinzufügen</button>
            </div>
        </form>
        </body>
    )
}
//onSubmit={onSubmit}

/*
<input type="[]" placeholder="Zutaten hier rein schreiben" onChange={onIngredientListChange} value={ingredientList}/>
    <textarea placeholder="Rezeptanleitung hier rein schreiben." onChange={onRecipeInstructionChange} value={recipeInstruction}/>
 */