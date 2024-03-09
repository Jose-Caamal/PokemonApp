package com.android.pokemon.ui.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.android.pokemon.repository.PokemonDBRepository
import com.android.pokemon.retrofit.models.PokemonModel

class PokemonViewModel: ViewModel() {
    private var pokemonDBRepository: PokemonDBRepository
    private var listPokemon: LiveData<List<PokemonModel>>

    init {
        pokemonDBRepository = PokemonDBRepository()
        listPokemon = pokemonDBRepository.listPokemon()!!
    }

    fun getListPokemon(): LiveData<List<PokemonModel>>{
        return listPokemon
    }
}