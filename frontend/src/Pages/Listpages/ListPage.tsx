import './ListPage.css';
import React, {ChangeEvent, FormEvent, useContext, useState} from "react";
import Header from "../../Components/Header";
import {useNavigate, useParams} from "react-router-dom";
import ArticleComponent from "../../Components/ArticleComponent";
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
    const [inputAmount, setInputAmount] = useState<string>("1")
    const {token} = useContext(AuthContext)

    if (!articleList) {
        navigate('/')
        return (
            <div>Liste existiert nicht</div>)
    }
    const delList = () => {
        deleteList(articleList.listId, token)
            .then(() => {
                refresh()
            })
            .then(() => {
                navigate('/vorrat')
            })
            .catch((er: any) => console.error(er))
    }

    const delArticle = (articleToRemove: string) => {
        deleteArticle(articleList.listId, articleToRemove, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const incrArticle = (articleToIncrease: string) => {
        increaseArticle(articleList.listId, articleToIncrease, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const decrArticle = (articleToDecrease: string) => {
        decreaseArticle(articleList.listId, articleToDecrease, token)
            .then(() => {
                refresh()
            })
            .catch((er: any) => console.error(er))
    }

    const handleChangeName = (event: ChangeEvent<HTMLInputElement>) => {
        setInputName(event.target.value)
    }

    const handleChangeAnzahl = (event: ChangeEvent<HTMLInputElement>) => {
        setInputAmount(event.target.value)
    }

    const handleChangeUnit = (event: ChangeEvent<HTMLInputElement>) => {
        setInputUnit(event.target.value)
    }

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        setInputName("")
        setInputAmount("1")
        setInputUnit("")
        event.preventDefault()
        const articleDto: ArticleDto = {
            name: inputName,
            amount: parseFloat(inputAmount),
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
                        <input type="number" placeholder="1" onChange={handleChangeAnzahl} value={inputAmount}/>
                        <input type="text" placeholder="Einheit" onChange={handleChangeUnit} value={inputUnit}/>
                    </div>
                    <div className="addArticle_button">
                        <button type={"submit"}>Artikel hinzufügen</button>
                    </div>
                </form>
                {articleList ?
                    articleList.listOfArticles.map((Article, i) =>
                        <ArticleComponent article={Article} key={i}
                                          removeArticle={articleToRemove => delArticle(articleToRemove)}
                                          decreaseAmount={articleToDecrease => decrArticle(articleToDecrease)}
                                          increaseAmount={articleToIncrease => incrArticle(articleToIncrease)}/>)
                    : "Liste existiert nicht"}
                <button onClick={delList}>Liste löschen</button>
            </div>
        </div>
    )
}