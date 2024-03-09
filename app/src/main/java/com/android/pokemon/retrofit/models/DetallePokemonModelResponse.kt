package com.android.pokemon.retrofit.models

data class DetallePokemonModelResponse(
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)