package ru.savenkov.surf_test.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.savenkov.surf_test.data.room.entities.CocktailEntity
import ru.savenkov.surf_test.data.room.entities.IngredientEntity
import ru.savenkov.surf_test.data.room.dao.CocktailDao

@Database(
    version = 1,
    entities = [CocktailEntity::class, IngredientEntity::class],
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCocktailDao(): CocktailDao

    /*companion object {
        @Volatile
        private var INSTANCE: AppDatabase?= null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cocktail_bar"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }*/
}