package github.sun5066.adventuretime.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import github.sun5066.adventuretime.R
import github.sun5066.adventuretime.ui.theme.AdventureTimeTheme
import github.sun5066.adventuretime.ui.wiget.CharacterList
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdventureTimeTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = getString(R.string.app_name))
                    })
                }) {
                    Surface(color = MaterialTheme.colors.background) {
                        CharacterList(viewModel)
                    }
                }
            }
        }
    }
}