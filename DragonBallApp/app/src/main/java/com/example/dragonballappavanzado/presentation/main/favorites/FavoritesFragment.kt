package com.example.dragonballappavanzado.presentation.main.favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dragonballappavanzado.R
import com.example.dragonballappavanzado.databinding.FragmentFavoritesBinding
import com.example.dragonballappavanzado.presentation.main.characters.CharactersViewState
import com.example.dragonballappavanzado.presentation.main.characters.adapter.CharactersAdapter
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI
import com.example.dragonballappavanzado.presentation.main.favorites.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    // VIEW BINDING
    private lateinit var binding: FragmentFavoritesBinding

    // RECYCLER VIEW - ADAPTER
    private lateinit var adapter: FavoritesAdapter

    // VIEW MODEL
    private val viewModel: FavoritesViewModel by viewModels()

    // LIFECYCLE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        viewModel.onViewAppear()
    }

    // FUNCTIONS
    private fun initUI() {
        initObservers()
        configureRecyclerView()
    }

    private fun initObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }
    }

    private fun configureRecyclerView() {
        adapter = FavoritesAdapter()
        binding.rvFavorites.setHasFixedSize(true)
        binding.rvFavorites.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvFavorites.adapter = adapter
    }

    private fun updateUI(viewState: FavoritesViewState) {
        when (viewState) {
            is FavoritesViewState.Idle -> idle()
            is FavoritesViewState.FavoriteCharactersLoaded -> updateAdapter(viewState.characters)
            is FavoritesViewState.Loading -> showLoading(viewState.loading)
        }
    }

    private fun idle() {}

    private fun updateAdapter(characters: List<CharacterUI>) {
        adapter.updateList(characters)
    }

    private fun showLoading(loading: Boolean) {
        binding.pbFavoritesLoading.isVisible = loading
        binding.favoritesToolbar.isVisible = !loading
        binding.rvFavorites.isVisible = !loading
    }
}