import NavBar from "../../Components/NavBar";
import Header from "../../Components/Header";
import Sidebar from "../../Components/Sidebar";
import React from "react";

export default function GewuerzePage(){
    return(
        <div>
            <NavBar />
            <Header title={"Gewürze"} />
            <Sidebar />
        </div>
    )
}