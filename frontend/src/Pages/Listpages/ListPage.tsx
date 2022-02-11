import './ListPage.css';
import React, {ChangeEvent, FormEvent, useContext, useState} from "react";
import Header from "../../Components/Header";
import {useNavigate, useParams} from "react-router-dom";
import ArtikelComponent from "../../Components/ArtikelComponent";
import Sidebar from "../../Components/Sidebar";
import {DataContext} from "../../Context/DataProvider";
import {decreaseArticle, deleteArticle, deleteList, increaseArticle, saveNewArticle} from "../../Services/apiService";
import {ArticleDto} from "../../Api/ArticleDto";
import {AuthContext} from "../../Context/AuthProvider";

export default function ListPage() {
    const {allList, refresh} = useContext(DataContext)
    const {listname} = useParams()
    const articleList = allList.find((List) => List.listName === listname)
    const navigate = useNavigate()
    const [input, setInput] = useState<string>("")
    const [zahl] = useState<number>(1)
    const {token} = useContext(AuthContext)

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

    const increaseArtikel = (artikelToIncrease: string) => {
        increaseArticle(articleList.listName, artikelToIncrease)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const decreaseArtikel = (artikelToDecrease: string) => {
        decreaseArticle(articleList.listName, artikelToDecrease)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const handleChangeName = (event: ChangeEvent<HTMLInputElement>) => {
        setInput(event.target.value)
    }

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const articleDto: ArticleDto = {
            name: input,
            anzahl: zahl
        }
        saveNewArticle(articleList.listName, articleDto, token)
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
                <form onSubmit={handleSubmit}>
                    <div className="addArticle_input">
                        <input type="text" placeholder="Artikel" onChange={handleChangeName} value={input}/>
                    </div>
                    <div className="addArticle_button">
                        <button type={"submit"}>Artikel hinzufügen</button>
                    </div>
                </form>
                {articleList ?
                    articleList.artikels.map((Artikel, i) =>
                        <ArtikelComponent artikel={Artikel} key={i}
                                          removeArtikel={artikelToRemove => delArtikel(artikelToRemove)}
                                          decreaseAnzahl={artikelToDecrease => decreaseArtikel(artikelToDecrease)}
                                          increaseAnzahl={artikelToIncrease => increaseArtikel(artikelToIncrease)}/>)
                    : "Liste existiert nicht"}
                <button onClick={entfernen}>Liste löschen</button>
            </div>
        </div>
    )
}