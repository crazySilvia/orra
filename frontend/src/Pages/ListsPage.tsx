import React, {useContext} from "react";
import Header from "../Components/Header";
import ListMenu from "../Components/ListMenu";
import AddListField from "../Components/AddListField";
import {DataContext} from "../Context/DataProvider";

export default function ListsPage() {
    
    const {allList} = useContext(DataContext)

    return (
        <div className="page">
            <Header title={"Vorrat"}/>
            <AddListField/>
            <ListMenu lists={allList}/>
        </div>
    )
}