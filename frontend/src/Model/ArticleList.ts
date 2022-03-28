import {IArticle} from "./Article";

export interface IArticleList {
    listId: string
    listName: string
    listOfArticles: IArticle[]
}