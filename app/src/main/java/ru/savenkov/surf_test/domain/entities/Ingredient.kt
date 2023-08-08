package ru.savenkov.surf_test.domain.entities

data class Ingredient(
    val id: Long = 0,
    val cocktailId: Long,
    val name: String
)
