package khs.onmi.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import khs.onmi.core.designsystem.R

@Composable
fun LoadingLottie(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
) {
    val loadingComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_lottie))

    LottieAnimation(
        modifier = modifier.size(size),
        composition = loadingComposition,
        iterations = LottieConstants.IterateForever
    )
}

@Preview
@Composable
fun LoadingLottiePreview() {
    LoadingLottie()
}