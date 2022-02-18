import {IIngredientList} from "./IngredientList";

export interface IRecipe {
    recipeName: string
    ingredientList: IIngredientList
    recipeInstructions: string
}