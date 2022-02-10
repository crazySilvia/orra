import './ListPage.css';
import React, {useContext} from "react";
import Header from "../../Components/Header";
import {useNavigate, useParams} from "react-router-dom";
import ArtikelComponent from "../../Components/ArtikelComponent";
import Sidebar from "../../Components/Sidebar";
import {DataContext} from "../../Context/DataProvider";
import {deleteArticle, deleteList} from "../../Services/apiService";

export default function ListPage() {
    const {allList, refresh} = useContext(DataContext)
    const {listname} = useParams()
    const articleList = allList.find((List) => List.listName === listname)
    const navigate = useNavigate()

    if (!articleList) {
        navigate('/')
        return (
            <div>Liste existiert nicht</div>)
    }
    const entfernen = () => {
        deleteList(articleList.listName)
            .then(() => {
                refresh()
            })
            .then(() => {
                navigate('/vorrat')
            })
            .catch((er: any) => console.error(er))
    }

    const delArtikel = (artikelToRemove: string) => {
        deleteArticle(articleList.listName, artikelToRemove)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    return (
        <div className="page">
            <div className="head">
                <Header title={articleList?.listName || "unbekannte Liste"}/>
            </div>
            <Sidebar lists={allList}/>
            <div className="content">
                {articleList ?
                    articleList.artikelList.map((Artikel, i) =>
                        <ArtikelComponent artikel={Artikel} key={i}
                                          removeArtikel={artikelToRemove => delArtikel(artikelToRemove)}/>)
                    : "Liste existiert nicht"}
                <button onClick={entfernen}>Liste löschen</button>
            </div>
        </div>
    )
}