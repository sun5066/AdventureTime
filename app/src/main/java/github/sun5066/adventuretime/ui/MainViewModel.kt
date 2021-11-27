package github.sun5066.adventuretime.ui

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.sun5066.adventuretime.R
import github.sun5066.adventuretime.util.ToastUtil
import github.sun5066.domain.usecase.AdventureTimeGetListUseCase
import github.sun5066.remote.data.CharacterInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val adventureTimeGetListUseCase: AdventureTimeGetListUseCase
) : ViewModel() {

    init {
        fetchData()
    }

    private val _list = MutableStateFlow(listOf<CharacterInfo>())
    val list get() = _list.asStateFlow()

    private fun fetchData() =
        viewModelScope.launch {
            adventureTimeGetListUseCase()
                .catch { ToastUtil.showToast(R.string.toast_network_error, Toast.LENGTH_LONG) }
                .collect { characterInfoList ->
                    _list.value = characterInfoList
                }
        }

}