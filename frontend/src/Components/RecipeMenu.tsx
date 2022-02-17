import "./ListMenu.css"
import React from 'react';
import {IRecipe} from "../Model/Recipe";
import RecipeMenuElement from "./RecipeMenuElement";

export default function RecipeMenu({lists}: { lists: IRecipe[] }) {
    return (
        <div className="menu">
            {lists.map((recipe: IRecipe, i) =>
                <RecipeMenuElement title={recipe.recipeName} key={i}/>)}
        </div>
    )
}