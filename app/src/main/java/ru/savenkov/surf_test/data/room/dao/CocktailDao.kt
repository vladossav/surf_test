package ru.savenkov.surf_test.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.savenkov.surf_test.data.room.entities.CocktailEntity
import ru.savenkov.surf_test.data.room.entities.IngredientEntity

@Dao
interface CocktailDao {

    @Query("SELECT * FROM 'cocktail' c WHERE c.id = :cocktailId")
    suspend fun getCocktailById(cocktailId: Long): CocktailEntity

    @Query("SELECT * FROM 'ingredients' c WHERE c.cocktailId = :cocktailId")
    suspend fun getIngredientsByCocktailId(cocktailId: Long): List<IngredientEntity>

    @Query("SELECT * FROM 'cocktail'")
    suspend fun getCocktails(): List<CocktailEntity>

    @Insert
    suspend fun insert(cocktail: CocktailEntity): Long

    @Insert
    suspend fun insert(ingredients: List<IngredientEntity>)

    @Transaction
    suspend fun insert(cocktail: CocktailEntity, ingredients: List<String>) {
        val id = insert(cocktail)
        val ingredientsEntityList = ingredients.map {ingredientName ->
            IngredientEntity(0, id, ingredientName)
        }
        insert(ingredientsEntityList)
    }
}