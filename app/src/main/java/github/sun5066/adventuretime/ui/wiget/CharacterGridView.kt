package github.sun5066.adventuretime.ui.wiget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import github.sun5066.adventuretime.R

@Composable
fun CharacterGridView(sprite: String, name: String) {
    Card(modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            val painter = rememberImagePainter(
                data = sprite,
                builder = {
                    crossfade(true)
                    crossfade(100)
                    placeholder(R.drawable.ic_launcher_foreground)
                }
            )

            Image(
                painter = painter,
                contentDescription = "Forest Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )

            Text(
                text = name,
                Modifier.width(150.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}