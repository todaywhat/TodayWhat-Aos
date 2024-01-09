package khs.onmi.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
fun MealItem(
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
                LargeAllergiesIcon(
                    tint = color.UnselectedPrimary,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}

@Composable
fun TimeTableItem(
    time: String,
    subject: String,
    modifier: Modifier = Modifier,
) {
    ONMITheme { color, typography ->
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color.CardBackground)
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "${time}교시",
                style = typography.Caption1,
                color = color.TextPrimary
            )
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .height(18.dp)
                    .background(color.UnselectedPrimary)
            )
            Text(
                text = subject,
                style = typography.Headline4,
                color = color.TextPrimary
            )
        }
    }
}

@Preview
@Composable
fun MealsItemPre() {
    MealItem(
        meal = "급식 메뉴",
        isAllergyFood = true,
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Preview
@Composable
fun TimeTableItemPre() {
    TimeTableItem(
        time = "1",
        subject = "한국사",
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}