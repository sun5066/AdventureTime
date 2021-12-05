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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import github.sun5066.adventuretime.R
import github.sun5066.adventuretime.ui.MainViewModel
import github.sun5066.adventuretime.ui.ResponseState
import github.sun5066.adventuretime.util.ToastUtil
import github.sun5066.remote.data.CharacterInfo

@ExperimentalFoundationApi
@Composable
fun CharacterGridList(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val state by mainViewModel.state.collectAsState()
    var refreshState by remember { mutableStateOf(false) }

    when(state) {
        is ResponseState.UnInitialize -> Unit
        is ResponseState.Loading -> {
            refreshState = false
            ProgressLoading()
        }
        is ResponseState.Success -> {
            LaunchedEffect(refreshState) {
                if (refreshState)
                    mainViewModel.fetchData()
            }
            SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = refreshState), onRefresh = { refreshState = true }) {
                CardGrid(
                    list = (state as ResponseState.Success).list,
                    navController = navController
                )
            }
        }
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
private fun CardGrid(list: List<CharacterInfo>, navController: NavHostController) {
    var clickedState by remember { mutableStateOf(true) }

    LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(10.dp)) {
        items(items = list, itemContent = { characterInfo ->
            CharacterCardView(characterInfo) {
                if (clickedState) {
                    navController.navigate("detail/${characterInfo.id}") {
                        launchSingleTop = true
                    }

                    clickedState = !clickedState
                }
            }
        })
    }
}