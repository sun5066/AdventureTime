package github.sun5066.adventuretime.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.sun5066.domain.usecase.AdventureTimeGetListUseCase
import github.sun5066.remote.data.CharacterInfo
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val adventureTimeGetListUseCase: AdventureTimeGetListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ResponseState>(ResponseState.UnInitialize)
    val state get() = _state.asStateFlow()

    fun fetchData() = viewModelScope.launch {
        adventureTimeGetListUseCase()
            .onStart { _state.value = ResponseState.Loading }
            .catch { error -> _state.value = ResponseState.Error(error) }
            .collect { list -> _state.value = ResponseState.Success(list) }
    }
}

sealed class ResponseState {
    object UnInitialize: ResponseState()
    object Loading: ResponseState()
    data class Error(val error: Throwable): ResponseState()
    data class Success(val list: List<CharacterInfo>): ResponseState()
}