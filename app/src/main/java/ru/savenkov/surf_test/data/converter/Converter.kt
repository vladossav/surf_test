package ru.savenkov.surf_test.data.converter

import ru.savenkov.surf_test.data.room.entities.CocktailEntity
import ru.savenkov.surf_test.data.room.entities.IngredientEntity
import ru.savenkov.surf_test.domain.entities.Cocktail
import ru.savenkov.surf_test.domain.entities.Ingredient

object Converter {

    fun convertCocktail(cocktail: Cocktail) = CocktailEntity(
        cocktail.id,
        cocktail.title,
        cocktail.description,
        cocktail.recipe,
        cocktail.imagePath
    )

    fun convertCocktail(cocktail: CocktailEntity) = Cocktail(
        cocktail.id,
        cocktail.title,
        cocktail.description,
        cocktail.recipe,
        cocktail.imagePath
    )


    fun convertIngredients(ingredients: List<IngredientEntity>) = ingredients.map { ingredient ->
        Ingredient(
            ingredient.id,
            ingredient.cocktailId,
            ingredient.name
        )
    }

}