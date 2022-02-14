import "./ListMenu.css"
import React from 'react';
import {IRecipe} from "../Model/Recipe";
import SidebarElement from "./SidebarElement";

export default function RecipeMenu({lists}:{lists:IRecipe[]}){
    return(
        <div className="menu">
            {lists.map((recipe: IRecipe, i)=>
            <SidebarElement title={recipe.recipeName} id={recipe.id} key={i}/>)}
        </div>
    )
}