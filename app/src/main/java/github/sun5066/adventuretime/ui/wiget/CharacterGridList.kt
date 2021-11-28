package github.sun5066.adventuretime.ui.wiget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import github.sun5066.adventuretime.ui.MainViewModel
import github.sun5066.remote.data.CharacterInfo

@ExperimentalFoundationApi
@Composable
fun CharacterGridList(
    mainViewModel: MainViewModel = viewModel(),
    showDetailProfileView: (CharacterInfo) -> Unit
) {
    val state by mainViewModel.list.collectAsState()

    LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(10.dp)) {
        items(items = state, itemContent = { characterInfo ->
            CharacterCardView(characterInfo.sprite, characterInfo.displayName) {
                showDetailProfileView.invoke(characterInfo)
            }
        })
    }
}