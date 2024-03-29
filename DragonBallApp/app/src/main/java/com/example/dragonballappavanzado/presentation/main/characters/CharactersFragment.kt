package com.example.dragonballappavanzado.presentation.main.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dragonballappavanzado.databinding.FragmentCharactersBinding
import com.example.dragonballappavanzado.presentation.main.characters.adapter.CharactersAdapter
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    // VIEW BINDING
    private lateinit var binding: FragmentCharactersBinding

    // RECYCLER VIEW - ADAPTER
    private lateinit var adapter: CharactersAdapter

    // VIEW MODEL
    private val viewModel: CharactersViewModel by viewModels()

    // LIFECYCLE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
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

    private fun updateUI(viewState: CharactersViewState) {
        when (viewState) {
            is CharactersViewState.Idle -> idle()
            is CharactersViewState.CharactersLoaded -> updateAdapter(viewState.characters)
            is CharactersViewState.Loading -> showLoading(viewState.loading)
        }
    }

    private fun updateAdapter(characters: List<CharacterUI>) {
        adapter.updateList(characters)
    }

    private fun showLoading(loading: Boolean) {
        binding.pbCharactersLoading.isVisible = loading
        binding.charactersToolbar.isVisible = !loading
        binding.rvCharacters.isVisible = !loading
    }

    private fun idle() {}

    private fun configureRecyclerView() {
        adapter = CharactersAdapter { characterId -> goToDetail(characterId) }
        binding.rvCharacters.setHasFixedSize(true)
        binding.rvCharacters.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvCharacters.adapter = adapter
    }

    private fun goToDetail(characterId: String) {
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailActivity(
                characterId = characterId
            )
        )
    }
}