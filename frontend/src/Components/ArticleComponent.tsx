import "./ArticleComponent.css"
import React from "react";
import {IArticle} from "../Model/Article";

interface ArticleProps{
    article: IArticle
    removeArticle(articleToRemove: string):void
    decreaseAmount(articleToDecrease: string):void
    increaseAmount(articleToIncrease: string):void
}

export default function ArticleComponent(props:ArticleProps){

    const {article, removeArticle, decreaseAmount, increaseAmount} = props;

    return(
        <div className="component">
            <button className="change" onClick={()=>{increaseAmount(article.name);}}>+</button>
            <p className="amount">{article.amount}</p>
            <button className="change" onClick={()=>{decreaseAmount(article.name);}}>-</button>
            <dl><dt>{article.unit}</dt><dd>{article.name}</dd></dl>

            <button className="x" onClick={()=>{removeArticle(article.name);}}>x</button>
        </div>
    )
}