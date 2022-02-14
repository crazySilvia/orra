import {IArtikel} from "./Artikel";

export interface IArtikelList{
    id: string
    listName: string
    artikels: IArtikel[]
}