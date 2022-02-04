import React from "react";
import NavBar from "../../Components/NavBar";
import Header from "../../Components/Header";
import Sidebar from "../../Components/Sidebar";

export default function SelbstgemachtesPage(){
    return(
        <div>
            <NavBar />
            <Header title={"Selbstgemacht"} />
            <Sidebar />
        </div>
    )
}