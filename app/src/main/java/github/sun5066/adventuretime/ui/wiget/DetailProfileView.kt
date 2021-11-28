package github.sun5066.adventuretime.ui.wiget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import github.sun5066.remote.data.CharacterInfo

@Composable
fun DetailProfileView(characterInfo: CharacterInfo) {
    val painter = rememberImagePainter(data = characterInfo.sprite)

    Image(painter = painter, contentDescription = "Image", modifier = Modifier.fillMaxSize())
}