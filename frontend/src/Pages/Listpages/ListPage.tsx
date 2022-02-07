import './ListPage.css';
import React, {useEffect, useState} from "react";
import NavBar from "../../Components/NavBar";
import Header from "../../Components/Header";
import {useParams} from "react-router-dom";
import {ArtikelList} from "../../Model/ArtikelList";
import ArtikelComponent from "../../Components/ArtikelComponent";
import Sidebar from "../../Components/Sidebar";
import {listnames} from "../../Services/apiService";

export default function ListPage({allLists}:{allLists:ArtikelList[]}){

    const{listname}=useParams()
    const articleList = allLists.find((List)=> List.listName === listname)
    const[articleLists, setarticleLists] = useState<ArtikelList[]>([])
    useEffect(() => {listnames().then(setarticleLists)},[])

    if(!articleList){
        return <div>
            Liste existiert nicht
        </div>
    }
    return(
        <div className="page">
            <div className="head">
                <NavBar />
                <Header title={articleList!.listName} />
            </div>
            <Sidebar lists={articleLists}/>
            <div className="content">
                {articleList.artikelList.map((Artikel)=>
                    <ArtikelComponent artikel={Artikel}/>)}
            </div>
        </div>
    )
}
