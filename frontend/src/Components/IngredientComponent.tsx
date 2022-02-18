import React from "react";

import {IIngredient} from "../Model/Ingredient";

interface IngredientProps {
    ingredient: IIngredient
    removeIngredient(ingredientToRemove: string): void
}

export default function IngredientComponent(props: IngredientProps) {

    const {ingredient, removeIngredient} = props;

    return (
        <div className="component">
            <div className="teil">
                <>{ingredient.ingredientAmount}</>
            </div>
            <div className="text">
                {ingredient.ingredientName}
            </div>
            <div className="teil">
                <button onClick={() => {
                    removeIngredient(ingredient.ingredientName);
                }}>x
                </button>
            </div>
        </div>
    )
}