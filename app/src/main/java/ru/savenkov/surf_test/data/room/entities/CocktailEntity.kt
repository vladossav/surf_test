package ru.savenkov.surf_test.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail")
data class CocktailEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String?,
    val recipe: String?,
    val imagePath: String?
)