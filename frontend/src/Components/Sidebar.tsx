import "./Sidebar.css"
import React from 'react';
import SidebarElement from "./SidebarElement";
import {IArtikelList} from "../Model/ArtikelList";

export default function Sidebar({lists}:{lists:IArtikelList[]}) {

    return(
        <div className="sidebar">
            {lists.map((artikelList, i)=>
                <SidebarElement title={artikelList.listName} id={artikelList.id} key={i}/>)}
        </div>
    )
}