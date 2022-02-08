import './ListMenu.css';
import React from 'react';
import {ArtikelList} from "../Model/ArtikelList";
import SidebarElement from "./SidebarElement";

export default function ListMenu({lists}:{lists:ArtikelList[]}){

    return(
        <div className="menu">
            {lists.map((artikelList)=>
                <SidebarElement title={artikelList.listName}/>)}
        </div>
    )
}