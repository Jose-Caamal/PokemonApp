package com.android.pokemon.retrofit

import com.android.pokemon.common.constantes
import okhttp3.Interceptor
import okhttp3.Response

class PokemonDBInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        //Añadimos parametros a la url de la cadena que recibimos
        var urlWithPams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(constantes.URL_PARAM_LANGUAGE, "es-ES")
            .build()

            var request = chain.request()
            request = request.newBuilder()
                .url(urlWithPams)
                .addHeader("Content-Type", "application/json")
                .addHeader("Acept","application/json")
                .build()
            return chain.proceed(request)
    }
}