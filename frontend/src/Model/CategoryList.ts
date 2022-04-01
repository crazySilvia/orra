import {IArticleList} from "./ArticleList";

export interface ICategoryList {
    catListId: string
    catListName: string
    listOfLists: IArticleList[]
}