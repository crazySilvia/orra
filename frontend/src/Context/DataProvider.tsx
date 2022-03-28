import React, {createContext, useContext, useEffect, useState} from "react";
import {IArticleList} from "../Model/ArticleList";
import {listnames} from "../Services/apiService";
import {AuthContext} from "./AuthProvider";

export interface IDataContextType {
    allList: IArticleList[],
    setAllList: (allList: IArticleList[]) => void
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
    const [allList, setAllList] = useState<IArticleList[]>([])
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