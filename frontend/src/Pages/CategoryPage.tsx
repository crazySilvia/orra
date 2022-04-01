import React, {useContext} from "react";
import Header from "../Components/Header";
import ListMenu from "../Components/ListMenu";
import AddListField from "../Components/AddListField";
import {DataContext} from "../Context/DataProvider";
import {category} from "../Services/apiService";
import {useParams} from "react-router-dom";
import {AuthContext} from "../Context/AuthProvider";
import AddCategoryField from "../Components/AddCategoryField";

export default function CategoryPage() {

    const {allList} = useContext(DataContext)
    const {allCategory} = useContext(DataContext)
    const {catListId} = useParams()
    const category = allCategory.find((CategoryList) => CategoryList.catListId === catListId)
    const {token} = useContext(AuthContext)

    return (
        <div className="page">
            <Header title={ category?.catListName || "unbekannte Kategorie"}/>
            <AddListField/>
            <ListMenu lists={allList}/>
        </div>
    )
}