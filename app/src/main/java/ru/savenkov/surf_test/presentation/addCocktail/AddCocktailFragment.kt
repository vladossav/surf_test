package ru.savenkov.surf_test.presentation.addCocktail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.surf_test.R
import ru.savenkov.surf_test.databinding.FragmentAddCocktailBinding

@AndroidEntryPoint
class AddCocktailFragment : Fragment() {
    private var _binding: FragmentAddCocktailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddCocktailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddCocktailBinding.inflate(inflater, container,false)

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.saveButton.setOnClickListener {
            if (viewModel.isCorrectInput(binding.titleInput, requireView())) {
                findNavController().popBackStack()
            }
            saveInput()
        }

        binding.addIngredientButton.setOnClickListener {
            findNavController().navigate(R.id.action_addCocktailFragment_to_ingredientDialogFragment)
        }

        setFragmentResultListener(IngredientDialogFragment.INGREDIENT_REQUEST_KEY) { _, bundle ->
            val ingredientName = bundle.getString(IngredientDialogFragment.INGREDIENT_NAME_KEY)
            addChipIngredient(inflater, ingredientName)
            viewModel.ingredientList.add(ingredientName!!)
        }

        return binding.root
    }

    private fun saveInput() {
        val title = binding.titleInput.text.toString()
        val description = binding.descriptionInput.text.toString()
        val recipe = binding.recipeInput.text.toString()
        viewModel.saveCocktail(title, description, recipe, null)
    }

    private fun addChipIngredient(inflater: LayoutInflater, ingredientName: String?) {
        val chip = inflater.inflate(R.layout.ingredient_chip, binding.ingredientContainer, false) as Chip
        chip.setOnCloseIconClickListener {
            binding.ingredientContainer.removeView(it)
            viewModel.ingredientList.removeIf {
                    str -> str == chip.text
            }
            Log.d("Ingredient", viewModel.ingredientList.toString())
        }
        chip.text = ingredientName
        chip.isCloseIconVisible = true
        binding.ingredientContainer.addView(chip, binding.ingredientContainer.childCount - 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}