import "./SidebarElement.css"
import React from "react";
import {Link} from "react-router-dom";

export default function RecipeMenuElement({title}:{title:string}){
    return(
        <Link to={"/rezepte"+title}>
            {title}
        </Link>
    )
}