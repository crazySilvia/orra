import "./SidebarElement.css"
import React from "react";

export default function SidebarElement({title}:{title:string}){
    return(
        <a href={"/"+title}>{title}</a>
        )
}