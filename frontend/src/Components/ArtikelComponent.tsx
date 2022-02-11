import "./ArtikelComponent.css"
import React from "react";
import {IArtikel} from "../Model/Artikel";


interface ArtikelProps{
    artikel: IArtikel
    removeArtikel(artikelToRemove: string):void
    decreaseAnzahl(artikelToDecrease: string):void
    increaseAnzahl(artikelToIncrease: string):void
}

export default function ArtikelComponent(props:ArtikelProps){

    const {artikel, removeArtikel, decreaseAnzahl, increaseAnzahl} = props;

    return(
        <div className="component">
            <div className="teil">
                <button>{artikel.anzahl}</button>
            </div>
            <div className="text">
                {artikel.name}
            </div>
            <div className="teil">
                <button onClick={()=>{increaseAnzahl(artikel.name);}}>+</button>
                <button onClick={()=>{decreaseAnzahl(artikel.name);}}>-</button>
                <button onClick={()=>{removeArtikel(artikel.name);}}>x</button>
            </div>
        </div>
    )
}