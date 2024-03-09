package com.android.pokemon.retrofit.models

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonModel>
)