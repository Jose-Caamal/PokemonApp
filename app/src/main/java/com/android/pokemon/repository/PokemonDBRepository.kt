package com.android.pokemon.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.pokemon.common.MyApp
import com.android.pokemon.retrofit.PokemonDBClient
import com.android.pokemon.retrofit.PokemonDBService
import com.android.pokemon.retrofit.models.PokemonModel
import com.android.pokemon.retrofit.models.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonDBRepository {
    var pokemonDBService: PokemonDBService? = null
    var pokemonDBClient: PokemonDBClient? = null
    var listPokemon: MutableLiveData<List<PokemonModel>>? = null

    init {
        pokemonDBClient = PokemonDBClient.instance
        pokemonDBService = pokemonDBClient?.getPokemonDBService()
        listPokemon = listPokemon()
    }

    fun listPokemon(): MutableLiveData<List<PokemonModel>>?{
        if(listPokemon == null){
            listPokemon = MutableLiveData<List<PokemonModel>>()
        }

        val call: Call<PokemonResponse>? = pokemonDBService?.getListPokemon()
        call?.enqueue(object : Callback<PokemonResponse>{
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful){
                    listPokemon?.value = response.body()?.results
                }
            }
        })
        return listPokemon
    }
}