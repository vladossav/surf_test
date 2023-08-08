package ru.savenkov.surf_test.data.repository

import ru.savenkov.surf_test.data.converter.Converter
import ru.savenkov.surf_test.data.room.dao.CocktailDao
import ru.savenkov.surf_test.domain.repository.CocktailRepository
import ru.savenkov.surf_test.domain.entities.Cocktail
import ru.savenkov.surf_test.domain.entities.Ingredient
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val dao: CocktailDao
): CocktailRepository {

    override suspend fun getAll(): List<Cocktail> {
        val cocktail = dao.getCocktails().map {
            Converter.convertCocktail(it)
        }
        return cocktail
    }

    override suspend fun getCocktailById(id: Long): Cocktail {
        val cocktail = dao.getCocktailById(id)
        return Converter.convertCocktail(cocktail)
    }

    override suspend fun getIngredientsByCocktailId(id: Long): List<Ingredient> {
        val ingredients = dao.getIngredientsByCocktailId(id)
        return Converter.convertIngredients(ingredients)
    }

    override suspend fun save(cocktail: Cocktail, ingredients: List<String>) {
        val cocktailEntity = Converter.convertCocktail(cocktail)
        dao.insert(cocktailEntity, ingredients)
    }


}