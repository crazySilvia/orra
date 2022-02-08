import "./SidebarElement.css"
import React from "react";
import {Link} from "react-router-dom";

export default function SidebarElement({title}:{title:string}){
    return(
        <Link to={"/"+title}>
            <div className="sidebarelement">
                <input value={title} />
            </div>
        </Link>
    )
}