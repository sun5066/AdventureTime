package github.sun5066.adventuretime.ui.wiget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import github.sun5066.remote.data.CharacterInfo

@Composable
fun DetailProfileView(characterInfo: CharacterInfo) {
    val scrollState = rememberScrollState()
    val imageOffset = -scrollState.value * 0.18f
    val painter = rememberImagePainter(data = characterInfo.sprite)

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(scrollState)
    ) {
        val (image, name, quotes, slug, sex) = remember { createRefs() }

        Image(
            painter = painter,
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .graphicsLayer { translationY = imageOffset }
                .constrainAs(image) {
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = characterInfo.fullName,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(name) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = characterInfo.slug,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(slug) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = characterInfo.sex,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 30.dp, top = 30.dp)
                .constrainAs(sex) {
                    top.linkTo(name.bottom)
                    start.linkTo(slug.end)
                }
        )

        Text(
            text = characterInfo.quote,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(quotes) {
                    top.linkTo(slug.bottom)
                    start.linkTo(parent.start)
                }
        )

    }
}