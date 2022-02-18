import React from 'react';
import {useContext} from "react";
import {DataContext} from "../Context/DataProvider";
import Header from "../Components/Header";
import RecipeMenu from "../Components/RecipeMenu";
import {Link} from "react-router-dom";

export default function RecipesPage(){
    const {allRecipe} = useContext(DataContext)

    return(
        <div className="page">
            <Header title={"Rezepte"} />
            <Link to={"/rezepte/erstellen"}>
                <button>Rezept erstellen</button>
            </Link>
            <RecipeMenu lists={allRecipe}/>
        </div>
    )
}