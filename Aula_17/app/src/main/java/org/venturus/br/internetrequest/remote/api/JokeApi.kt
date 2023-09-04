package org.venturus.br.internetrequest.remote.api

import org.venturus.br.internetrequest.remote.model.JokeResponse
import retrofit2.http.GET

internal interface JokeApi {

    @GET("/random_joke")
    suspend fun getRandomJoke(): JokeResponse
}
