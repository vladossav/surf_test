package ru.savenkov.surf_test.presentation.detailCocktail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.surf_test.R
import ru.savenkov.surf_test.databinding.FragmentDetailsBinding
import ru.savenkov.surf_test.domain.entities.Ingredient
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    @Inject
    lateinit var factory: DetailsFragmentViewModel.Factory

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsFragmentViewModel by viewModels {
        viewModelFactory {
            initializer {
                factory.create(getCocktailId())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        viewModel.cocktail.observe(viewLifecycleOwner) {cocktail ->
            binding.title.text = cocktail.title
            binding.description.text = cocktail.description ?: ""
            if (!cocktail.recipe.isNullOrEmpty()) addRecipe(inflater, cocktail.recipe)
        }

        viewModel.ingredients.observe(viewLifecycleOwner) {list ->
            if (!list.isNullOrEmpty()) addIngredients(inflater, list)
        }

        return binding.root
    }

    private fun addRecipe(inflater: LayoutInflater, recipe: String) {
        binding.recipeContainer.isVisible = true
        val textView = inflater.inflate(R.layout.text_recipe, binding.recipeContainer, false) as TextView
        textView.text = recipe
        binding.recipeContainer.addView(textView)
    }

    private fun addIngredients(inflater: LayoutInflater, ingredients: List<Ingredient>) {
        binding.ingredientContainer.isVisible = true
        ingredients.forEach {ingredient ->
            val textView = inflater.inflate(R.layout.text_recipe, binding.ingredientContainer, false) as TextView
            textView.text = ingredient.name
            binding.ingredientContainer.addView(textView)
        }
    }

    private fun getCocktailId(): Long =
        if (arguments?.containsKey(COCKTAIL_ID_KEY) == true) {
            val loanId = requireArguments().getLong(COCKTAIL_ID_KEY)
            loanId
        } else -1

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val COCKTAIL_ID_KEY = "COCKTAIL_ID_KEY"
    }
}