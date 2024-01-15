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
    breakfast: Pair<List<String>, String>,
    lunch: Pair<List<String>, String>,
    dinner: Pair<List<String>, String>,
) {
    ONMITheme { color, _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(32.dp)) }
            breakfast.first.takeIf { it.isNotEmpty() }?.let { meals ->
                item {
                    MealsItem(
                        name = "아침",
                        kcal = breakfast.second,
                        meals = meals
                    )
                    Divider(
                        thickness = 1.dp,
                        color = color.UnselectedSecondary,
                        modifier = Modifier.padding(vertical = 32.dp)
                    )
                }
            }
            lunch.first.takeIf { it.isNotEmpty() }?.let { meals ->
                item {
                    MealsItem(
                        name = "점심",
                        kcal = lunch.second,
                        meals = meals
                    )
                }
            }
            dinner.first.takeIf { it.isNotEmpty() }?.let { meals ->
                item {
                    Divider(
                        thickness = 1.dp,
                        color = color.UnselectedSecondary,
                        modifier = Modifier.padding(vertical = 32.dp)
                    )
                    MealsItem(
                        name = "저녁",
                        kcal = dinner.second,
                        meals = meals
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

    MealsSection(
        breakfast = Pair(mealsDummy, "123.4 Kcal"),
        lunch = Pair(mealsDummy, "123.4 Kcal"),
        dinner = Pair(mealsDummy, "123.4 Kcal")
    )
}