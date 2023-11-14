package khs.onmi.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.AllergiesEggIcon
import khs.onmi.core.designsystem.icon.InfoCardMealIcon
import khs.onmi.core.designsystem.icon.InfoCardTimeTableIcon
import khs.onmi.core.designsystem.modifier.addBounceEffect
import khs.onmi.core.designsystem.modifier.onmiClickable
import khs.onmi.core.designsystem.theme.ONMITheme

@Composable
fun AllergiesCard(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    itemNumber: Int,
    allergyName: String,
    allergyIcon: @Composable () -> Unit,
    onItemClick: () -> Unit,
) {
    ONMITheme { color, typography ->
        Box(
            modifier = modifier
                .addBounceEffect()
                .onmiClickable(rippleEnabled = false, onClick = onItemClick)
                .clip(RoundedCornerShape(16.dp))
                .background(color.CardBackgroundSecondary)
                .border(
                    2.dp,
                    if (isSelected) color.Black else Color.Transparent,
                    RoundedCornerShape(16.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    allergyIcon()
                    RowSpacer(dp = 8.dp)
                    Text(
                        text = allergyName,
                        style = typography.Body3,
                        color = if (isSelected) color.TextPrimary else color.UnselectedPrimary
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-8).dp, y = 8.dp)
            ) {
                Text(
                    text = itemNumber.toString(),
                    style = typography.Body3,
                    color = if (isSelected) color.TextPrimary else color.UnselectedPrimary,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    isMeal: Boolean,
    school: String,
    grade: Int,
    `class`: Int,
) {
    ONMITheme { color, typography ->
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color.CardBackground)
        ) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterEnd),
                visible = isMeal,
                enter = slideIn(tween(400, easing = LinearOutSlowInEasing)) { fullSize ->
                    IntOffset(0, -400)
                },
                exit = slideOut(tween(400, easing = FastOutSlowInEasing)) {
                    IntOffset(0, -400)
                },
            ) {
                InfoCardMealIcon(
                    tint = color.UnselectedSecondary,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-11).dp, y = 22.dp)
                )
            }
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterEnd),
                visible = !isMeal,
                enter = slideIn(tween(400, easing = LinearOutSlowInEasing)) { fullSize ->
                    IntOffset(0, 400)
                },
                exit = slideOut(tween(400, easing = FastOutSlowInEasing)) {
                    IntOffset(0, 400)
                },
            ) {
                InfoCardTimeTableIcon(
                    tint = color.UnselectedSecondary,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-11).dp, y = 22.dp)
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 22.dp)
            ) {
                Text(
                    text = school,
                    style = typography.Headline4,
                    color = color.Black
                )
                ColumnSpacer(dp = 8.dp)
                Text(
                    text = "${grade}학년 ${`class`}반",
                    style = typography.Body2,
                    color = color.UnselectedPrimary
                )
            }
        }
    }
}

@Preview
@Composable
fun AllergiesCardPre() {
    var isSelected by remember {
        mutableStateOf(false)
    }

    AllergiesCard(
        modifier = Modifier
            .width(167.dp)
            .height(96.dp),
        isSelected = isSelected,
        itemNumber = 1,
        allergyName = "난류",
        allergyIcon = {
            AllergiesEggIcon(isItemSelected = isSelected)
        },
        onItemClick = {
            isSelected = !isSelected
        }
    )
}

@Preview
@Composable
fun InfoCardPre() {
    var isMeal by remember {
        mutableStateOf(true)
    }

    Column {
        InfoCard(
            modifier = Modifier
                .height(95.dp)
                .fillMaxWidth(),
            isMeal = isMeal,
            school = "광주소프트웨어마이스터고등학교",
            grade = 2,
            `class` = 1
        )

        Button(onClick = { isMeal = !isMeal }) {
            Text(text = "애니메이션 동작")
        }
    }
}