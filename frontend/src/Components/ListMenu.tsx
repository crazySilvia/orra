import './ListMenu.css';
import React from 'react';
import SidebarElement from "./SidebarElement";
import {IArtikelList} from "../Model/ArtikelList";

export default function ListMenu({lists}:{lists:IArtikelList[]}){

    return(
        <div className="menu">
            {lists.map((artikelList, i)=>
                <SidebarElement title={artikelList.listName} id={artikelList.listId} key={i}/>)}
        </div>
    )
}