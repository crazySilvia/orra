import React, {useEffect, useState} from "react";
import NavBar from "../Components/NavBar";
import Header from "../Components/Header";
import ListMenu from "../Components/ListMenu";
import {ArtikelList} from "../Model/ArtikelList";
import {listnames} from "../Services/apiService";

export default function ListsPage(){

    const[articleLists, setarticleLists] = useState<ArtikelList[]>([])
    useEffect(() => {listnames().then(setarticleLists)},[])

    return(
        <div className="page">
            <NavBar />
            <Header title={"Vorrat"} />
            <body>
                <ListMenu lists={articleLists}/>
            </body>
        </div>
    )
}