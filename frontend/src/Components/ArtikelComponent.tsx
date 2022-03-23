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
            <button className="change" onClick={()=>{increaseAnzahl(artikel.name);}}>+</button>
            <p className="amount">{artikel.anzahl}</p>
            <button className="change" onClick={()=>{decreaseAnzahl(artikel.name);}}>-</button>
            <dl><dt>{artikel.unit}</dt><dd>{artikel.name}</dd></dl>

            <button className="x" onClick={()=>{removeArtikel(artikel.name);}}>x</button>
        </div>
    )
}

//<p>{artikel.unit}</p>
//             <p className="text">
//                 {artikel.name}
//             </p>