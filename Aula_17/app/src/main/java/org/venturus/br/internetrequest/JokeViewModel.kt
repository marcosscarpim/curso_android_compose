package org.venturus.br.internetrequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.venturus.br.internetrequest.remote.api.JokeService
import org.venturus.br.internetrequest.remote.model.JokeResponse

internal class JokeViewModel : ViewModel() {

    private val jokeService = JokeService()

    private val _jokeData: MutableStateFlow<JokeResponse?> = MutableStateFlow(null)
    val jokeData: StateFlow<JokeResponse?> = _jokeData

    init {
        loadJoke()
    }

    fun loadJoke() = viewModelScope.launch {
        val joke = jokeService.jokeApi.getRandomJoke()
        _jokeData.value = joke
    }
}