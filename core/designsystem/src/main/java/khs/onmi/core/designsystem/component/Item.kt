package khs.onmi.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.LargeAllergiesIcon
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun MealsItem(
    meal: String,
    isAllergyFood: Boolean,
    modifier: Modifier = Modifier,
) {
    ONMITheme { color, typography ->
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color.CardBackground)
                .padding(horizontal = 12.dp)
        ) {
            Text(
                text = meal,
                style = typography.Headline4,
                color = color.TextPrimary,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            if (isAllergyFood) {
                LargeAllergiesIcon(modifier = Modifier.align(Alignment.CenterEnd))
            }
        }
    }
}

@Preview
@Composable
fun MealsItemPre() {
    MealsItem(
        meal = "급식 메뉴",
        isAllergyFood = true,
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}