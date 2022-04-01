import React, {useContext} from "react";
import Header from "../Components/Header";
import ListMenu from "../Components/ListMenu";
import AddListField from "../Components/AddListField";
import {DataContext} from "../Context/DataProvider";
import AddCategoryField from "../Components/AddCategoryField";
import CategoryMenu from "../Components/CategoryMenu";

export default function VorratPage() {
    
    const {allCategory} = useContext(DataContext)

    return (
        <div className="page">
            <Header title={"Vorrat"}/>
            <AddCategoryField/>
            <CategoryMenu list={allCategory}/>
        </div>
    )
}