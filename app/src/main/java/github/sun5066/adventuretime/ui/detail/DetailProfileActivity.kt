package github.sun5066.adventuretime.ui.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import github.sun5066.adventuretime.R
import github.sun5066.adventuretime.ui.theme.AdventureTimeTheme
import github.sun5066.adventuretime.ui.wiget.DetailProfileView
import github.sun5066.remote.data.CharacterInfo

class DetailProfileActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdventureTimeTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Image(
                            painter = painterResource(id = R.drawable.back_arrow),
                            contentDescription = "BackPress",
                            modifier = Modifier
                                .size(36.dp)
                                .clickable { finish() }
                        )
                    })
                }) {
                    Surface(color = MaterialTheme.colors.background) {
                        val characterInfo = intent.getParcelableExtra<CharacterInfo>("characterInfo")!!
                        DetailProfileView(characterInfo)
                    }
                }
            }
        }
    }
}