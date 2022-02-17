import React, {createContext, useEffect, useContext, useState} from "react";
import {IArtikelList} from "../Model/ArtikelList";
import {listnames, recipes} from "../Services/apiService";
import {IRecipe} from "../Model/Recipe";
import {AuthContext} from "./AuthProvider";

export interface IDataContextType {
    allList: IArtikelList[],
    allRecipe: IRecipe[],
    setAllRecipe: (allRecipe: IRecipe[]) => void,
    setAllList: (allList: IArtikelList[]) => void,
    refresh: () => void,
    refreshRecipes: () => void
}

export const DataContext = createContext<IDataContextType>({
    setAllList: () => {},
    allList: [],
    setAllRecipe: () => {},
    allRecipe: [],
    refresh: () => {},
    refreshRecipes: () => {}
})

export default function DataProvider({children}: { children: React.ReactNode }) {
    const [allList, setAllList] = useState<IArtikelList[]>([])
    const [allRecipe, setAllRecipe] = useState<IRecipe[]>([])
    const {token} = useContext(AuthContext)

    useEffect(() => {
            refresh()
            refreshRecipes()
        }
        , [token])

    const refresh = () => {
        listnames(token).then(setAllList)
    }

    const refreshRecipes = () => {
        recipes(token).then(setAllRecipe)
    }

    return (
        <DataContext.Provider value={{allList, setAllList, allRecipe, setAllRecipe, refresh, refreshRecipes}}>
            {children}
        </DataContext.Provider>
    )
}