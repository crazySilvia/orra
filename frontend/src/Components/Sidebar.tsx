import "./Sidebar.css"
import React from 'react';
import SidebarElement from "./SidebarElement";
import {ArtikelList} from "../Model/ArtikelList";

export default function Sidebar({lists}:{lists:ArtikelList[]}) {

    return(
        <div className="sidebar">
            {lists.map((artikelList)=>
                <SidebarElement title={artikelList.listName}/>)}
        </div>
    )
}