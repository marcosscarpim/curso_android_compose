package org.venturus.br.internetrequest.remote.model

data class JokeResponse(
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
)