import React, {useEffect} from "react";
import {createContext, useState} from "react";
import {IArtikelList} from "../Model/ArtikelList";
import {listnames} from "../Services/apiService";


export interface IDataContextType{
    allList: IArtikelList[],
    setAllList: (allList: IArtikelList[]) => void
    refresh: ()=>void
}

export const DataContext = createContext<IDataContextType>({
    setAllList: () => {},
    allList: [],
    refresh: () => {}
})

export default function DataProvider({children} : {children: React.ReactNode}) {
    const [allList, setAllList] = useState<IArtikelList[]>([])

    useEffect(() => {refresh()},[])

    const refresh = () => {listnames().then(setAllList)}

    return (
        <DataContext.Provider value={{allList, setAllList, refresh}}>
            {children}
        </DataContext.Provider>
    )
}