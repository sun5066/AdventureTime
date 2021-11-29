package github.sun5066.adventuretime.ui.wiget

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import github.sun5066.adventuretime.R
import github.sun5066.adventuretime.ui.MainViewModel
import github.sun5066.adventuretime.ui.ResponseState
import github.sun5066.adventuretime.util.ToastUtil
import github.sun5066.remote.data.CharacterInfo

@ExperimentalFoundationApi
@Composable
fun CharacterGridList(
    mainViewModel: MainViewModel,
    showDetailProfileView: (CharacterInfo) -> Unit
) {
    val state by mainViewModel.state.collectAsState()

    when(state) {
        is ResponseState.UnInitialize -> Unit
        is ResponseState.Loading -> ProgressLoading()
        is ResponseState.Success -> CardGrid(
            list = (state as ResponseState.Success).list,
            showDetailProfileView = showDetailProfileView
        )
        is ResponseState.Error -> ToastUtil.showToast(
            R.string.toast_network_error,
            Toast.LENGTH_LONG
        )
    }
}

@Composable
private fun ProgressLoading() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        color = MaterialTheme.colors.primary
    )
}

@ExperimentalFoundationApi
@Composable
private fun CardGrid(list: List<CharacterInfo>, showDetailProfileView: (CharacterInfo) -> Unit) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(10.dp)) {
        items(items = list, itemContent = { characterInfo ->
            CharacterCardView(characterInfo.sprite, characterInfo.displayName) {
                showDetailProfileView.invoke(characterInfo)
            }
        })
    }
}