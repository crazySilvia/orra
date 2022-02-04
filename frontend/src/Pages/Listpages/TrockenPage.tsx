import NavBar from "../../Components/NavBar";
import Header from "../../Components/Header";
import Sidebar from "../../Components/Sidebar";
import React from "react";

export default function TrockenPage(){
    return(
        <div>
            <NavBar />
            <Header title={"Trocken"} />
            <Sidebar />
        </div>
    )
}