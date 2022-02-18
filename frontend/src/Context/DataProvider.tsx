import React, {createContext, useContext, useEffect, useState} from "react";
import {IArtikelList} from "../Model/ArtikelList";
import {listnames} from "../Services/apiService";
import {AuthContext} from "./AuthProvider";

export interface IDataContextType {
    allList: IArtikelList[],
    setAllList: (allList: IArtikelList[]) => void
    refresh: () => void
}

export const DataContext = createContext<IDataContextType>({
    setAllList: () => {
    },
    allList: [],
    refresh: () => {
    }
})

export default function DataProvider({children}: { children: React.ReactNode }) {
    const [allList, setAllList] = useState<IArtikelList[]>([])
    const {token} = useContext(AuthContext)


    useEffect(() => {
            refresh()
        }
        //eslint-disable-next-line
        , [token])

    const refresh = () => {
        listnames(token).then(setAllList)
    }


    return (
        <DataContext.Provider value={{allList, setAllList, refresh}}>
            {children}
        </DataContext.Provider>
    )
}