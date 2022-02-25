import "./Sidebar.css"
import React from 'react';
import RecipeSidebarElement from "./RecipeSidebarElement";
import {IRecipe} from "../Model/Recipe";

export default function RecipeSidebar({lists}: { lists: IRecipe[] }) {

    return (
        <div className="sidebar">
            {lists.map((recipe, i) =>
                <RecipeSidebarElement title={recipe.recipeName} id={recipe.recipeName} key={i}/>)}
        </div>
    )
}