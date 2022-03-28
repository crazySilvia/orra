import './ListMenu.css';
import React from 'react';
import SidebarElement from "./SidebarElement";
import {IArticleList} from "../Model/ArticleList";

export default function ListMenu({lists}:{lists:IArticleList[]}){

    return(
        <div className="menu">
            {lists.map((articleList, i)=>
                <SidebarElement title={articleList.listName} id={articleList.listId} key={i}/>)}
        </div>
    )
}