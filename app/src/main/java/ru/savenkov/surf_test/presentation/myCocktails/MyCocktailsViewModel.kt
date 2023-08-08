package ru.savenkov.surf_test.presentation.myCocktails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.surf_test.domain.CocktailRepository
import ru.savenkov.surf_test.domain.entities.Cocktail
import javax.inject.Inject

@HiltViewModel
class MyCocktailsViewModel @Inject constructor(
    private val repository: CocktailRepository
): ViewModel() {

    private val _cocktailList = MutableLiveData<List<Cocktail>>()
    val cocktailList: LiveData<List<Cocktail>> = _cocktailList

    fun getCocktailList() {
        viewModelScope.launch(Dispatchers.IO) {
            val cocktails = repository.getAll()
            _cocktailList.postValue(cocktails)
        }
    }
}