package com.example.dragonballappavanzado.utils

import com.example.dragonballappavanzado.data.remote.response.CharacterRemote
import com.example.dragonballappavanzado.data.remote.response.LocationRemote
import com.example.dragonballappavanzado.domain.models.CharacterLocal
import com.example.dragonballappavanzado.presentation.main.characters.model.CharacterUI

fun generateLocalCharacters(size: Int) =
    (0 until size).map { CharacterLocal("id$it", "name$it", "photo$it", "description$it", false) }

fun generateRemoteCharacters(size: Int) =
    (0 until size).map { CharacterRemote("id$it", "name$it", "photo$it", "description$it", false) }

fun generateUICharacters(size: Int) =
    (0 until size).map { CharacterUI("id$it", "name$it", "photo$it") }

fun generateRemoteLocations(size: Int) =
    (0 until size).map { LocationRemote("$it", "$it") }