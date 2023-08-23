package org.venturus.br.aula13

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class UserProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUIState(isLoading = true))
    val uiState: StateFlow<AppUIState> = _uiState

    fun loadProfile() {
        val userInfo = UserInfo(
            profileImage = R.mipmap.ic_launcher,
            headerImage = R.drawable.ic_launcher_background,
            name = R.string.user_name,
            description = R.string.user_description,
            bio = R.string.user_bio
        )
        _uiState.value = AppUIState(userInfo = userInfo)
    }
}

data class AppUIState(
    val isLoading: Boolean = false,
    val userInfo: UserInfo? = null,
    val error: Throwable? = null
)
