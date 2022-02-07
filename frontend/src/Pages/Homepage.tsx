import './Pages.css';
import React from "react";
import Header from "../Components/Header";
import NavBar from "../Components/NavBar";

export default function Homepage(){
    return(
        <div className="page">
            <div className="head">
                <NavBar />
                <Header title="OrrA"/>
            </div>
            <body>
            ...
            </body>
        </div>
    )
}