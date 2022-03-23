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
    const {listId} = useParams()
    const articleList = allList.find((List) => List.listId === listId)
    const navigate = useNavigate()
    const [inputName, setInputName] = useState<string>("")
    const [inputUnit, setInputUnit] = useState<string>("")
    const [inputAnzahl, setInputAnzahl] = useState<string>("1")
    const {token} = useContext(AuthContext)

    if (!articleList) {
        navigate('/')
        return (
            <div>Liste existiert nicht</div>)
    }
    const entfernen = () => {
        deleteList(articleList.listId, token)
            .then(() => {
                refresh()
            })
            .then(() => {
                navigate('/vorrat')
            })
            .catch((er: any) => console.error(er))
    }

    const delArtikel = (artikelToRemove: string) => {
        deleteArticle(articleList.listId, artikelToRemove, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const increaseArtikel = (artikelToIncrease: string) => {
        increaseArticle(articleList.listId, artikelToIncrease, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const decreaseArtikel = (artikelToDecrease: string) => {
        decreaseArticle(articleList.listId, artikelToDecrease, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const handleChangeName = (event: ChangeEvent<HTMLInputElement>) => {
        setInputName(event.target.value)
    }

    const handleChangeAnzahl = (event: ChangeEvent<HTMLInputElement>) => {
        setInputAnzahl(event.target.value)
    }

    const handleChangeUnit = (event: ChangeEvent<HTMLInputElement>) => {
        setInputUnit(event.target.value)
    }

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const articleDto: ArticleDto = {
            name: inputName,
            anzahl: parseFloat(inputAnzahl),
            unit: inputUnit
        }
        saveNewArticle(articleList.listId, articleDto, token)
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
                        <input type="text" placeholder="Artikel" onChange={handleChangeName} value={inputName}/>
                        <input type="number" placeholder="1" onChange={handleChangeAnzahl} value={inputAnzahl}/>
                        <input type="text" placeholder="Einheit" onChange={handleChangeUnit} value={inputUnit}/>
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