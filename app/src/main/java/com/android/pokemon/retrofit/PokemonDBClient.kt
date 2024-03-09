package com.android.pokemon.retrofit

import com.android.pokemon.common.constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDBClient {
    private val pokemonDBService: PokemonDBService
    private val retrofit: Retrofit

    companion object{
        var instance: PokemonDBClient? = null
            get() {
                if(field == null){
                    instance = PokemonDBClient()
                }
                return field
            }
    }

    init{
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(PokemonDBInterceptor())

        val cliente = okHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(constantes.POKEMONDBAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()

        pokemonDBService = retrofit.create(PokemonDBService::class.java)
    }

    fun getPokemonDBService() = pokemonDBService
}