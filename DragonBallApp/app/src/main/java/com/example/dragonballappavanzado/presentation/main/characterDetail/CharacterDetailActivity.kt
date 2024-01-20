package com.example.dragonballappavanzado.presentation.main.characterDetail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgs
import com.example.dragonballappavanzado.R
import com.example.dragonballappavanzado.databinding.ActivityCharacterDetailBinding
import com.example.dragonballappavanzado.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : AppCompatActivity(), OnMapReadyCallback {
    // VIEW BINDING
    private lateinit var binding: ActivityCharacterDetailBinding

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

        BottomSheetBehavior.from(binding.bottomSheet).apply {
            peekHeight = 300
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    // FUNCTIONS
    private fun initUI() {
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.tvCharactersTitle.text = args.characterId
        binding.ivBtnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initObservers() {

    }

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
    }
}