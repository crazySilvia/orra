import {IIngredient} from "./Ingredient";

export interface IRecipe{
    recipeName: string
    ingredientList: IIngredient[]
    recipeInstructions: string
}