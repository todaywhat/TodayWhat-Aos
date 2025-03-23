package khs.onmi.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onmi.domain.model.meal.response.GetMealsResponseModel
import com.onmi.domain.usecase.meal.MealException
import com.onmi.domain.usecase.meal.MealState
import khs.onmi.core.designsystem.component.ONMIButton
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun MealsSection(
    state: MealState,
    onReloadClick: () -> Unit,
) {
    when (state) {
        is MealState.Success -> {
            with(state.response) {
                MealsSectionItem(
                    breakfast = breakfast,
                    lunch = lunch,
                    dinner = dinner
                )
            }
        }

        is MealState.Failure -> {
            MealsSectionErrorItem(
                mealException = state.exception,
                onReloadClick = onReloadClick
            )
        }

        is MealState.Loading -> {}
    }
}

@Composable
fun MealsSectionItem(
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

@Composable
fun MealsSectionErrorItem(
    mealException: MealException,
    onReloadClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (mealException) {
                MealException.DataEmpty -> {
                    Text(
                        text = "급식 정보가 없습니다.",
                        style = typography.Body1,
                        color = color.TextSecondary,
                    )
                }

                MealException.InternetDisconnected -> {
                    Text(
                        text = "인터넷이 연결되지 않았습니다.\n와이파이 혹은 데이터를 연결 해주세요.",
                        style = typography.Body1,
                        color = color.TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }

                is MealException.Unknown -> {
                    Text(
                        text = "급식 정보를 불러오지 못했습니다.",
                        style = typography.Body1,
                        color = color.TextSecondary,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ONMIButton(
                        isEnabled = true,
                        text = "다시 불러오기",
                        colors = ButtonDefaults.buttonColors(
                            contentColor = color.White,
                            containerColor = color.Black,
                        ),
                        onClick = onReloadClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MealsSectionPre() {
    val mealsDummy = listOf("쌀밥", "쇠고기미역국", "소불고기", "배추김치", "무생채", "유기농요구르트")

    MealsSection(
        state = MealState.Success(
            response = GetMealsResponseModel(
                breakfast = Pair(mealsDummy, "123.4 Kcal"),
                lunch = Pair(mealsDummy, "123.4 Kcal"),
                dinner = Pair(mealsDummy, "123.4 Kcal")
            )
        ),
        onReloadClick = {}
    )
}