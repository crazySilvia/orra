import {IArtikel} from "./Artikel";

export interface IArtikelList{
    listId: string
    listName: string
    artikels: IArtikel[]
}