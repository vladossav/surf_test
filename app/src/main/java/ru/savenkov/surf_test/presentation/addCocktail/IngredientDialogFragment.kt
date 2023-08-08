package ru.savenkov.surf_test.presentation.addCocktail


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import ru.savenkov.surf_test.databinding.FragmentIngredientDialogBinding

class IngredientDialogFragment: DialogFragment() {
    private var _binding: FragmentIngredientDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            val ingredientName = binding.ingredientInput.text.toString()
            if (ingredientName.isBlank()) {
                binding.ingredientInput.error = "Field is empty!"
                return@setOnClickListener
            }
            setFragmentResult(
                INGREDIENT_REQUEST_KEY,
                bundleOf(INGREDIENT_NAME_KEY to ingredientName)
            )
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val INGREDIENT_NAME_KEY = "INGREDIENT_NAME_KEY"
        const val INGREDIENT_REQUEST_KEY = "INGREDIENT_REQUEST_KEY"
    }
}