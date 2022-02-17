import React, {useContext} from "react";
import {DataContext} from "../Context/DataProvider";
import {useNavigate, useParams} from "react-router-dom";
import {AuthContext} from "../Context/AuthProvider";
import Header from "../Components/Header";
import Sidebar from "../Components/Sidebar";

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

    return (
        <div className="page">
            <div>
                <Header title={recipe?.recipeName || "unbekanntes Rezept"}/>
            </div>


        </div>
    )
}