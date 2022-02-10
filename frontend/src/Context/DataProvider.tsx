import React, {useEffect} from "react";
import {createContext, useState} from "react";
import {IArtikelList} from "../Model/ArtikelList";
import {listnames} from "../Services/apiService";


export interface IDataContextType{
    allList: IArtikelList[],
    setAllList: (allList: IArtikelList[]) => void
}

export const DataContext = createContext<IDataContextType>({
    setAllList: () => {},
    allList: []
})

export default function DataProvider({children} : {children: React.ReactNode}) {
    const [allList, setAllList] = useState<IArtikelList[]>([])
    useEffect(() => {listnames().then(setAllList)},[allList])

    return (
        <DataContext.Provider value={{allList, setAllList}}>
            {children}
        </DataContext.Provider>
    )
}