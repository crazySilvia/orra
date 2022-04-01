import React, {createContext, useContext, useEffect, useState} from "react";
import {IArticleList} from "../Model/ArticleList";
import {category, listnames} from "../Services/apiService";
import {AuthContext} from "./AuthProvider";
import {ICategoryList} from "../Model/CategoryList";

export interface IDataContextType {
    allList: IArticleList[],
    setAllList: (allList: IArticleList[]) => void
    refresh: () => void
    allCategory: ICategoryList[]
    setAllCategory:  (allCategory: ICategoryList[])  => void
    refreshCategory: () => void
}

export const DataContext = createContext<IDataContextType>({
    setAllList: () => {
    },
    allList: [],
    refresh: () => {
    },
    setAllCategory: () => {},
    allCategory: [],
    refreshCategory: () => {}
})

export default function DataProvider({children}: { children: React.ReactNode }) {
    const [allList, setAllList] = useState<IArticleList[]>([])
    const [allCategory, setAllCategory] = useState<ICategoryList[]>([])
    const {token} = useContext(AuthContext)

    useEffect(() => {
        refresh()
        refreshCategory()
        }
        //eslint-disable-next-line
        , [token])

    const refresh = () => {
        listnames(token).then(setAllList)
    }

    const refreshCategory = () => {
        category(token).then(setAllCategory)
    }

    return (
        <DataContext.Provider value={{allList, setAllList, refresh, allCategory, setAllCategory, refreshCategory}}>
            {children}
        </DataContext.Provider>
    )
}