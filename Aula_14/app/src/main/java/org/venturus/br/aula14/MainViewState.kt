package org.venturus.br.aula14

import androidx.annotation.StringRes

sealed class MainViewState(@StringRes val messageId: Int) {

    object Idle : MainViewState(R.string.main_state_idle)

    object Loading : MainViewState(R.string.main_state_loading)

    object Success : MainViewState(R.string.main_state_success)

    object Executing : MainViewState(R.string.main_state_executing)
}
