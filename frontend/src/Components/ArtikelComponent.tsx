import "./ArtikelComponent.css"
import React from "react";
import {IArticle} from "../Model/Article";

interface ArtikelProps{
    artikel: IArticle
    removeArtikel(artikelToRemove: string):void
    decreaseAnzahl(artikelToDecrease: string):void
    increaseAnzahl(artikelToIncrease: string):void
}

export default function ArtikelComponent(props:ArtikelProps){

    const {artikel, removeArtikel, decreaseAnzahl, increaseAnzahl} = props;

    return(
        <div className="component">
            <button className="change" onClick={()=>{increaseAnzahl(artikel.name);}}>+</button>
            <p className="amount">{artikel.amount}</p>
            <button className="change" onClick={()=>{decreaseAnzahl(artikel.name);}}>-</button>
            <dl><dt>{artikel.unit}</dt><dd>{artikel.name}</dd></dl>

            <button className="x" onClick={()=>{removeArtikel(artikel.name);}}>x</button>
        </div>
    )
}