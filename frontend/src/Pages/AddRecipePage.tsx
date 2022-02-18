import React, {ChangeEvent, useContext, useState} from 'react';
import {AuthContext} from "../Context/AuthProvider";
import {useNavigate} from "react-router-dom";
import Header from "../Components/Header";
import {IIngredient} from "../Model/Ingredient";
import IngredientComponent from "../Components/IngredientComponent";
import {DataContext} from "../Context/DataProvider";
import {deleteArticle} from "../Services/apiService";

export default function AddRecipePage() {

    const {allRecipe, refresh} = useContext(DataContext)
    const [recipeName, setRecipeName] = useState<string>()
    const [ingredientList, setIngredientList] = useState<string[]>()
    const [recipeInstruction, setRecipeInstruction] = useState<string>()
    const recipe

    const {token} = useContext(AuthContext)
    const navigate = useNavigate()

    if(!recipe) {
        navigate('/rezepte')
        return(
            <div>Rezept existiert nicht</div>
        )
    }

    const delIngredient = (ingredientToRemove: string) => {
        deleteIngredient(ingredientList.ingredientListId, ingredientToRemove, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const onRecipeNameChange = (event: ChangeEvent<HTMLInputElement>) => {
        setRecipeName(event.target.value)
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
        <Header title={"Rezepte"}/>
        <form>
            <div>
                <input type="text" placeholder="Rezeptname" onChange={onRecipeNameChange} value={recipeName}/>
                {ingredientList ?
                ingredientList.ingredient.map((Ingredient: IIngredient, i:number) =>
                <IngredientComponent ingredient={Ingredient} key={i}
                                     removeIngredient={ingredientToRemove => delIngredient(ingredientToRemove)}/>)
                : "Keine Zutaten vorhanden."}
                <input type="[]" placeholder="Zutaten hier rein schreiben" value={ingredientList}/>
                <textarea placeholder="Rezeptanleitung hier rein schreiben." value={recipeInstruction}/>
            </div>
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