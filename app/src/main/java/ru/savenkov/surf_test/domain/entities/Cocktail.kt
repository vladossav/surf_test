package ru.savenkov.surf_test.domain.entities

data class Cocktail(
    val id: Long = 0,
    val title: String,
    val description: String?,
    val recipe: String?,
    val imagePath: String?
)
