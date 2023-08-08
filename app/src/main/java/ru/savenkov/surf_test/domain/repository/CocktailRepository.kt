package ru.savenkov.surf_test.domain.repository

import ru.savenkov.surf_test.domain.entities.Cocktail
import ru.savenkov.surf_test.domain.entities.Ingredient

interface CocktailRepository {
    suspend fun getAll(): List<Cocktail>
    suspend fun getCocktailById(id: Long): Cocktail
    suspend fun getIngredientsByCocktailId(id: Long): List<Ingredient>
    suspend fun save(cocktail: Cocktail, ingredients: List<String>)
}