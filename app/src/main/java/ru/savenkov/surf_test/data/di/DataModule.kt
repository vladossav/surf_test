package ru.savenkov.surf_test.data.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.savenkov.surf_test.data.repository.CocktailRepositoryImpl
import ru.savenkov.surf_test.data.room.AppDatabase
import ru.savenkov.surf_test.data.room.dao.CocktailDao
import ru.savenkov.surf_test.domain.CocktailRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    companion object {

        @Provides
        fun provideCocktailDao(database: AppDatabase): CocktailDao {
            return database.getCocktailDao()
        }

        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "cocktail_bar"
            ).fallbackToDestructiveMigration().build()

    }

    //@Singleton
    @Binds
    fun bindCocktailRepository(impl: CocktailRepositoryImpl): CocktailRepository
}