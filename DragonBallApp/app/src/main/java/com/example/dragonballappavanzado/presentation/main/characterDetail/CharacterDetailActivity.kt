package com.example.dragonballappavanzado.presentation.main.characterDetail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import coil.load
import com.example.dragonballappavanzado.R
import com.example.dragonballappavanzado.databinding.ActivityCharacterDetailBinding
import com.example.dragonballappavanzado.presentation.main.characterDetail.model.CharacterDetailUI
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    // VIEW BINDING
    private lateinit var binding: ActivityCharacterDetailBinding

    // VIEW MODEL
    private val viewModel: CharacterDetailActivityViewModel by viewModels()

    // SAFE ARGS
    private val args: CharacterDetailActivityArgs by navArgs()

    // GOOGLE MAPS
    private lateinit var mMap: GoogleMap

    // LIFECYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        viewModel.onViewAppear(args.characterId)
    }

    // FUNCTIONS
    private fun initUI() {
        initListeners()
        initObservers()
        initMap()
        configureBottomSheetBehavior()
    }

    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun configureBottomSheetBehavior() {
        BottomSheetBehavior.from(binding.bottomSheet).apply {
            peekHeight = 300
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun initListeners() {
        binding.tvCharactersTitle.text = args.characterId
        binding.ivBtnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.ivBtnNoFavorite.setOnClickListener {
            updateCharacterFavoriteStatus()
        }
        binding.ivBtnFavorite.setOnClickListener {
            updateCharacterFavoriteStatus()
        }
    }

    private fun updateCharacterFavoriteStatus() {
        if (binding.ivBtnNoFavorite.isVisible) {
            binding.ivBtnNoFavorite.isVisible = !binding.ivBtnNoFavorite.isVisible
            binding.ivBtnFavorite.isVisible = !binding.ivBtnFavorite.isVisible
            viewModel.onFavoriteClicked(args.characterId, true)
        } else if (binding.ivBtnFavorite.isVisible) {
            binding.ivBtnFavorite.isVisible = !binding.ivBtnFavorite.isVisible
            binding.ivBtnNoFavorite.isVisible = !binding.ivBtnNoFavorite.isVisible
            viewModel.onFavoriteClicked(args.characterId, false)
        }
    }

    private fun initObservers() {
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.viewState.collect { viewState ->
                updateUI(viewState)
            }
        }
    }

    private fun updateUI(viewState: CharacterDetailViewState) {
        when (viewState) {
            is CharacterDetailViewState.Idle -> idle()
            is CharacterDetailViewState.CharacterLoaded -> updateCharacterInformation(viewState.character)
            is CharacterDetailViewState.Loading -> showLoading(viewState.loading)
        }
    }

    private fun showLoading(loading: Boolean) {
        binding.loadingView.isVisible = loading
        binding.loadingProgressBarDetail.isVisible = loading
    }

    private fun updateCharacterInformation(character: CharacterDetailUI) {
        binding.tvCharactersTitle.text = character.name
        binding.tvCharacterDetailName.text = character.name
        binding.tvCharacterDetailDescription.text = character.description
        binding.ivCharacterDetail.load(character.photo)
        binding.ivBtnNoFavorite.isVisible = !character.favorite
        binding.ivBtnFavorite.isVisible = character.favorite
    }

    private fun idle() { }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.addCircle(
            CircleOptions().center(sydney).radius(12000.0).fillColor(Color.CYAN).strokeColor(Color.BLUE).strokeWidth(1f)
        )
        viewModel.onMapLoaded()
    }
}