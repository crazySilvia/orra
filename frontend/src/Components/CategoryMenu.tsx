import './ListMenu.css';
import React from 'react';
import {ICategoryList} from "../Model/CategoryList";
import SidebarElement from "./SidebarElement";

export default function CategoryMenu({list}:{list:ICategoryList[]}){
    return(
        <div className="menu">
            {list.map((categoryList, i) =>
            <SidebarElement title={categoryList.catListName} id={categoryList.catListId} key={i}/>)}
        </div>
    )
}