package khs.onmi.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.component.ONMIButton
import khs.onmi.core.designsystem.theme.ONMITheme

// todo State 수정 필요
sealed interface MealsSectionState {
    data object Success : MealsSectionState
    data object Failure : MealsSectionState
    data object Empty : MealsSectionState
}

@Composable
fun MealsSection(
    state: MealsSectionState,
    breakfast: Pair<List<String>, String>,
    lunch: Pair<List<String>, String>,
    dinner: Pair<List<String>, String>,
) {
    when(state) {
        is MealsSectionState.Success -> {
            MealsSectionItem(
                breakfast = breakfast,
                lunch = lunch,
                dinner = dinner
            )
        }
        is MealsSectionState.Failure -> {
            MealsSectionErrorItem {  }
        }
        is MealsSectionState.Empty -> {
            MealsSectionEmptyItem {  }
        }
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
fun MealsSectionErrorItem(onClick: () -> Unit) {
    ONMITheme { color, typography ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // todo 에러 정의 후 수정 필요
            if (false) {
                Text(
                    text = "인터넷이 연결되지 않았습니다.\n와이파이 혹은 데이터를 연결 해주세요.",
                    style = typography.Body1,
                    color = color.TextSecondary,
                    textAlign = TextAlign.Center
                )
            } else {
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
                        contentColor = color.Black,
                        containerColor = color.White,
                    ),
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
fun MealsSectionEmptyItem(onClick: () -> Unit) {
    ONMITheme { color, typography ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "급식 정보가 없습니다.",
                style = typography.Body1,
                color = color.TextSecondary,
            )
            Spacer(modifier = Modifier.height(16.dp))
            ONMIButton(
                isEnabled = true,
                text = "문의하기",
                colors = ButtonDefaults.buttonColors(
                    contentColor = color.Black,
                    containerColor = color.White,
                ),
                onClick = onClick
            )
        }
    }
}


@Preview
@Composable
fun MealsSectionPre() {
    val mealsDummy = listOf("쌀밥", "쇠고기미역국", "소불고기", "배추김치", "무생채", "유기농요구르트")

    MealsSection(
        state = MealsSectionState.Success,
        breakfast = Pair(mealsDummy, "123.4 Kcal"),
        lunch = Pair(mealsDummy, "123.4 Kcal"),
        dinner = Pair(mealsDummy, "123.4 Kcal")
    )
}