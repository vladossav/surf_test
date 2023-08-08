package ru.savenkov.surf_test.data.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "ingredients",
    foreignKeys = [ForeignKey(
        entity = CocktailEntity::class,
        childColumns = ["cocktailId"],
        parentColumns = ["id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cocktailId: Long,
    val name: String
)
