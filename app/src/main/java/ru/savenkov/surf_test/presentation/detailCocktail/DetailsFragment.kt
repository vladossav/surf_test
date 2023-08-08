package ru.savenkov.surf_test.presentation.detailCocktail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.surf_test.databinding.FragmentDetailsBinding
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

        }

        return binding.root
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