package com.android.pokemon.retrofit

import com.android.pokemon.retrofit.models.DetallePokemonModelResponse
import com.android.pokemon.retrofit.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDBService {
    @GET("pokemon?limit=1154")
    fun getListPokemon(): Call<PokemonResponse>

    @GET("pokemon/{id}")
    fun getDetailsPokemon(@Path("id") id: Int): Call<DetallePokemonModelResponse>
}