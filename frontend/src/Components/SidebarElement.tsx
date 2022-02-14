import "./SidebarElement.css"
import React from "react";
import {Link} from "react-router-dom";

export default function SidebarElement({title, id}:{title:string, id: string}){
    return(
        <Link to={"/"+id}>
            {title}
        </Link>
    )
}