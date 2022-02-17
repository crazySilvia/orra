import {IIngredient} from "../Model/Ingredient";

export  interface NewRecipeDto {
    recipeName: string
    ingredientList: IIngredient[]
    recipeInstruction: string
}