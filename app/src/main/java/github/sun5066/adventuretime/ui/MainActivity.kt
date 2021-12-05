package github.sun5066.adventuretime.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import github.sun5066.adventuretime.R
import github.sun5066.adventuretime.config.NavigationConfig
import github.sun5066.adventuretime.ui.theme.AdventureTimeTheme
import github.sun5066.adventuretime.ui.wiget.CharacterGridList
import github.sun5066.adventuretime.ui.wiget.DetailProfileView
import github.sun5066.adventuretime.util.ToastUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()

        setContent {
            AdventureTimeTheme {
                val navController = rememberAnimatedNavController()
                // A surface container using the 'background' color from the theme
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = getString(R.string.app_name))
                    })
                }) {
                    Surface(color = MaterialTheme.colors.background) {
                        NavigationComponent(navController, viewModel)
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun NavigationComponent(navHostController: NavHostController, viewModel: MainViewModel) {
    AnimatedNavHost(
        navController = navHostController,
        startDestination = NavigationConfig.NAVIGATION_MENU_MAIN
    ) {
        val springSpec = spring<IntOffset>(dampingRatio = Spring.DampingRatioHighBouncy)

        composable(NavigationConfig.NAVIGATION_MENU_MAIN) {
            CharacterGridList(viewModel, navHostController)
        }
        composable(
            route = NavigationConfig.NAVIGATION_MENU_DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
            enterTransition = {
                slideInVertically(initialOffsetY = { 1000 }, animationSpec = springSpec)
            },
            exitTransition = {
                shrinkHorizontally()
            }
        ) {
            val id = it.arguments!!.getInt("id")

            viewModel.findById(id)?.also { characterInfo ->
                DetailProfileView(characterInfo)
            } ?: ToastUtil.showToast(R.string.not_found_character_info, Toast.LENGTH_LONG)
        }
    }
}
