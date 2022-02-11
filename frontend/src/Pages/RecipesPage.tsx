import React from 'react';
import {useContext} from "react";
import {DataContext} from "../Context/DataProvider";
import Header from "../Components/Header";
import RecipeMenu from "../Components/RecipeMenu";

export default function RecipesPage(){
    const {allRecipe} = useContext(DataContext)

    return(
        <div className="page">
            <Header title={"Rezepte"} />
            <RecipeMenu lists={allRecipe}/>
        </div>
    )
}