import "./ArtikelComponent.css"
import React from "react";
import {IArtikel} from "../Model/Artikel";

export default function ArtikelComponent({artikel}:{artikel:IArtikel}){

    return(
        <div className="component">
            <div className="teil">
                <button>{artikel.anzahl}</button>
            </div>
            <div className="text">
                {artikel.name}
            </div>
            <div className="teil">
                <button >+</button>
                <button >-</button>
                <button >x</button>
            </div>
        </div>
    )
}