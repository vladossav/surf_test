package ru.savenkov.surf_test.presentation.myCocktails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.savenkov.surf_test.R
import ru.savenkov.surf_test.databinding.FragmentMyCocktailsBinding
import ru.savenkov.surf_test.presentation.detailCocktail.DetailsFragment

@AndroidEntryPoint
class MyCocktailsFragment : Fragment() {
    private var _binding: FragmentMyCocktailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyCocktailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyCocktailsBinding.inflate(inflater, container, false)

        if (savedInstanceState == null) viewModel.getCocktailList()

        val cocktailAdapter = CocktailAdapter{cocktailId ->
            findNavController().navigate(R.id.action_myCocktailsFragment_to_detailsFragment,
                bundleOf(DetailsFragment.COCKTAIL_ID_KEY to cocktailId)
                )
        }
        binding.recycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = cocktailAdapter
        }

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_myCocktailsFragment_to_blankFragment)
        }

        viewModel.cocktailList.observe(viewLifecycleOwner) {list ->
            binding.mainLogo.isVisible = list.isEmpty()
            binding.arrowImage.isVisible = list.isEmpty()
            binding.addGuideLabel.isVisible = list.isEmpty()
            cocktailAdapter.cocktailList = list
            Log.d("Cocktail", list.toString())
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCocktailList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}