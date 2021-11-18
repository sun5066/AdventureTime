package github.sun5066.adventuretime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import github.sun5066.adventuretime.ui.theme.AdventureTimeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdventureTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(mainViewModel: MainViewModel = viewModel()) {
    val state by mainViewModel.list.collectAsState()

    LazyColumn {
        items(items = state, itemContent = {
            val painter =
                rememberImagePainter(
                    data = it.sprite,
                    builder = {
                        crossfade(true)
                        crossfade(100)
                        placeholder(R.drawable.ic_launcher_foreground)
                    }
                )

            Image(
                painter = painter,
                contentDescription = "Forest Image",
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )

            Text(text = "Hi, ${it.fullName}")
        })
    }
}