package ru.savenkov.surf_test.presentation.detailCocktail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.surf_test.domain.CocktailRepository
import ru.savenkov.surf_test.domain.entities.Cocktail
import ru.savenkov.surf_test.domain.entities.Ingredient

class DetailsFragmentViewModel @AssistedInject constructor(
    @Assisted loanId: Long,
    private val repository: CocktailRepository
): ViewModel() {

    private val _cocktail = MutableLiveData<Cocktail>()
    val cocktail: LiveData<Cocktail> = _cocktail
    private val _ingredients = MutableLiveData<List<Ingredient>>()
    val ingredients: LiveData<List<Ingredient>> = _ingredients

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val cocktailResult = repository.getCocktailById(loanId)
            val ingredientsResult = repository.getIngredientsByCocktailId(loanId)
            _cocktail.postValue(cocktailResult)
            _ingredients.postValue(ingredientsResult)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(loanId: Long): DetailsFragmentViewModel
    }
}