import React, {useContext} from "react";
import {DataContext} from "../Context/DataProvider";
import {useNavigate, useParams} from "react-router-dom";
import {AuthContext} from "../Context/AuthProvider";
import Header from "../Components/Header";
import Sidebar from "../Components/Sidebar";
import RecipeSidebar from "../Components/RecipeSidebar";
import {deleteIngredient} from "../Services/apiService";

export default function RecipePage() {
    const {allRecipe, refresh} = useContext(DataContext)
    const {recipeName} = useParams()
    const navigate = useNavigate()
    const {token} = useContext(AuthContext)
    const recipe = allRecipe.find((List) => List.recipeName === recipeName)

    if (!recipe) {
        navigate('/rezepte')
        return (
            <div>Rezept existiert nicht</div>
        )
    }
    const remove = () => {
        deleteRecipe(recipe.recipeName, token)
            .then(() => {
                refresh()
            })
            .then(() => {
                navigate('/rezepte')
            })
            .catch((er: any) => console.error(er))
    }

    const delIngredient = (ingredientToRemove: String) => {
        deleteIngredient(recipe.recipeName, ingredientToRemove, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    return (
        <div className="page">
            <div>
                <Header title={recipe?.recipeName || "unbekanntes Rezept"}/>
            </div>
            <RecipeSidebar lists={allRecipe}/>

        </div>
    )
}