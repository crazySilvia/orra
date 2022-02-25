import "./SidebarElement.css"
import React from "react";
import {Link} from "react-router-dom";

export default function RecipeSidebarElement({title, id}:{title: string, id: string}){
    return(
        <Link to={"/rezepte"+id}>
            {title}
        </Link>
    )
}