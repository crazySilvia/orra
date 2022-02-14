import {IIngredient} from "./Ingredient";

export interface IRecipe{
    recipeName: string
    ingredientList: IIngredient[]
    id: string
}