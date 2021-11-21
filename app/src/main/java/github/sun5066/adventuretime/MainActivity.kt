package github.sun5066.adventuretime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import github.sun5066.adventuretime.ui.theme.AdventureTimeTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalFoundationApi
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

@ExperimentalFoundationApi
@Composable
fun Greeting(mainViewModel: MainViewModel = viewModel()) {
    val state by mainViewModel.list.collectAsState()

    LazyVerticalGrid(cells = GridCells.Adaptive(150.dp)) {
        items(items = state, itemContent = {
            ConstraintLayout(modifier = Modifier.background(color = Color.LightGray)) {
                val (image, text) = createRefs()

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
                    modifier = Modifier.size(200.dp).constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    contentScale = ContentScale.Crop
                )

                Text(text = it.fullName, Modifier.constrainAs(text) {
                    top.linkTo(image.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
        })
    }
}