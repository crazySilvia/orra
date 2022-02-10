import "./ArtikelComponent.css"
import React from "react";
import {IArtikel} from "../Model/Artikel";


interface ArtikelProps{
    artikel: IArtikel
    removeArtikel(artikelToRemove: string):void
}

export default function ArtikelComponent(props:ArtikelProps){

    const increaseAnzahl = () => {

    }

    const decreaseAnzahl = () => {

    }

    const {artikel, removeArtikel} = props;

    return(
        <div className="component">
            <div className="teil">
                <button>{artikel.anzahl}</button>
            </div>
            <div className="text">
                {artikel.name}
            </div>
            <div className="teil">
                <button onClick={increaseAnzahl}>+</button>
                <button onClick={decreaseAnzahl}>-</button>
                <button onClick={()=>{removeArtikel(artikel.name);}}>x</button>
            </div>
        </div>
    )
}