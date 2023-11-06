package khs.onmi.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.icon.AllergiesEggIcon
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
                    Spacer(modifier = Modifier.width(8.dp))
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