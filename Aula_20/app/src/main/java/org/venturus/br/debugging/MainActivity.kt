package org.venturus.br.debugging

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults.contentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.jetbrains.annotations.Async
import org.venturus.br.debugging.ui.theme.DebuggingTheme
import org.venturus.br.debugging.ui.theme.Neutral4
import org.venturus.br.debugging.ui.theme.Neutral6
import org.venturus.br.debugging.ui.theme.Ocean10
import org.venturus.br.debugging.ui.theme.Shadow4
import kotlin.math.max
import kotlin.math.min

private const val VNT_LOGO_URL =
    "https://www.venturus.org.br/wp-content/uploads/Assets/marca/simbolo_colorido.png"

private val TitleHeight = 128.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 56.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebuggingTheme {
                VntScreen()
            }
        }
    }
}

@Composable
fun VntScreen() {
    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)

        Header()
        Body(scroll)
        Title(scroll.value)
        VntImage(scroll.value)
    }
}

@Composable
fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(listOf(Shadow4, Ocean10)))
    )
}

@Composable
fun Title(scroll: Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }
    val offset = (maxOffset - scroll).coerceAtLeast(minOffset)

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .offset {
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(Color.White)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Venturus",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = HzPadding
        )
        Text(
            text = "Centro de Inovação e Tecnologia",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = HzPadding
        )
        Spacer(Modifier.height(8.dp))
        Divider(
            color = Neutral4,
            thickness = 1.dp
        )
    }
}

@Composable
fun VntImage(scroll: Int) {
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scroll / collapseRange).coerceIn(0f, 1f)
    }
    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.then(Modifier.statusBarsPadding())
    ) {
        Box(
            modifier = Modifier
                .shadow(elevation = 0.dp, shape = CircleShape, clip = false)
                .zIndex(0f)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(VNT_LOGO_URL)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}

@Composable
fun Body(scroll: ScrollState) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 0.dp, shape = RectangleShape, clip = false)
                    .zIndex(0f)
                    .background(
                        color = Color.White,
                        shape = RectangleShape
                    )
                    .clip(RectangleShape)
            ) {
                CompositionLocalProvider(LocalContentColor provides contentColor) {
                    Column {
                        Spacer(Modifier.height(ImageOverlap))
                        Spacer(Modifier.height(TitleHeight))
                        Spacer(Modifier.height(16.dp))

                        Text(
                            text = "Details",
                            style = MaterialTheme.typography.titleMedium,
                            color = Neutral6,
                            modifier = HzPadding
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.detail_placeholder),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Neutral6,
                            modifier = HzPadding
                        )

                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.detail_placeholder),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Neutral6,
                            modifier = HzPadding
                        )

                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.detail_placeholder),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Neutral6,
                            modifier = HzPadding
                        )

                        Spacer(Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DebuggingTheme {
        VntScreen()
    }
}