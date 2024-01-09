package khs.onmi.main.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun MealsSection(
    breakfast: List<String>?,
    lunch: List<String>?,
    dinner: List<String>?,
) {
    ONMITheme { color, _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(32.dp)) }
            breakfast?.let {
                item {
                    MealsItem(
                        name = "아침",
                        kcal = 124,
                        meals = breakfast
                    )
                }
            }
            lunch?.let {
                item {
                    Divider(
                        thickness = 1.dp,
                        color = color.UnselectedSecondary,
                        modifier = Modifier.padding(vertical = 32.dp)
                    )
                    MealsItem(
                        name = "점심",
                        kcal = 396,
                        meals = lunch
                    )
                }
            }
            dinner?.let {
                item {
                    Divider(
                        thickness = 1.dp,
                        color = color.UnselectedSecondary,
                        modifier = Modifier.padding(vertical = 32.dp)
                    )
                    MealsItem(
                        name = "저녁",
                        kcal = 1980,
                        meals = dinner
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}


@Preview
@Composable
fun MealsSectionPre() {
    val mealsDummy = listOf("쌀밥", "쇠고기미역국", "소불고기", "배추김치", "무생채", "유기농요구르트")

    MealsSection(breakfast = mealsDummy, lunch = mealsDummy, dinner = mealsDummy)
}