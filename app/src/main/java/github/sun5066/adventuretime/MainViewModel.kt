package github.sun5066.adventuretime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.sun5066.domain.usecase.AdventureTimeGetListUseCase
import github.sun5066.remote.RestApiManager
import github.sun5066.remote.data.CharacterInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    init {
        fetchData()
    }

    private val _list = MutableStateFlow(listOf<CharacterInfo>())
    val list get() = _list.asStateFlow()

    private fun fetchData() =
        viewModelScope.launch {
            val service = RestApiManager.makeAdventureTimeService()
            AdventureTimeGetListUseCase(service).invoke().collect { characterInfoList ->
                _list.value = characterInfoList
            }
        }

}