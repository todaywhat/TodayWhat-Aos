package khs.onmi.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.MealItem
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun MealsItem(
    name: String,
    kcal: Int,
    meals: List<String>,
) {
    ONMITheme { color, typography ->
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {
                Text(
                    text = name,
                    modifier = Modifier.align(Alignment.TopStart),
                    style = typography.Body2,
                    color = color.TextSecondary
                )
                Text(
                    text = "$kcal Kcal",
                    modifier = Modifier.align(Alignment.TopEnd),
                    style = typography.Body2,
                    color = color.TextSecondary
                )
            }
            meals.forEach { meal ->
                MealItem(
                    modifier = Modifier
                        .height(52.dp)
                        .fillMaxWidth(),
                    meal = meal,
                    isAllergyFood = false
                )
            }
        }
    }
}