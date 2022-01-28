import "./Header.css"
import React from "react";

export default function Header({title}: {title: string}){
    return(
        <header className="header">
            {title}
        </header>
    )
}