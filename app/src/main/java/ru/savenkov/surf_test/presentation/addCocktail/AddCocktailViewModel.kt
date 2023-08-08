package ru.savenkov.surf_test.presentation.addCocktail

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.surf_test.domain.CocktailRepository
import ru.savenkov.surf_test.domain.entities.Cocktail
import ru.savenkov.surf_test.util.showSnackbar
import javax.inject.Inject

@HiltViewModel
class AddCocktailViewModel @Inject constructor(
    private val repository: CocktailRepository
): ViewModel() {

    val ingredientList = ArrayList<String>()

    fun isCorrectInput(title: TextInputEditText, view: View): Boolean {
        if (title.text.toString().isBlank())
            title.error = "Field is empty!"
        if (ingredientList.isEmpty())
            view.showSnackbar("Must have at least one ingredient!")

        if (title.text.toString().isBlank() || ingredientList.isEmpty())
            return false
        return true
    }

    fun saveCocktail(title: String, description: String?, recipe: String?, imagePath: String?) {
        val cocktail = Cocktail(0, title, description, recipe, imagePath)
        viewModelScope.launch(Dispatchers.IO) {
            repository.save(cocktail, ingredientList)
        }
    }

}