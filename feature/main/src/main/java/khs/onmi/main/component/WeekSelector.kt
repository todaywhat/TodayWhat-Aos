package khs.onmi.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import khs.onmi.core.designsystem.icon.DownIcon
import khs.onmi.core.designsystem.modifier.onmiClickable
import khs.onmi.core.designsystem.theme.ONMITheme
import kotlin.math.roundToInt

/**
 * 메인 화면에서 조회할 주차. 임시로 feature:main 에 두었으며, UseCase 연동 시 domain 으로 이동 예정.
 */
enum class WeekType(val label: String) {
    LAST_WEEK(label = "저번 주"),
    THIS_WEEK(label = "이번 주"),
    NEXT_WEEK(label = "다음 주"),
}

private val SelectorPanelTopGap = 8.dp
private val SelectorPanelHorizontalMargin = 16.dp
private val SelectorScrimColor = Color.Black.copy(alpha = 0.6f)

/**
 * 탑바에 놓이는 "이번 주 ▼" 트리거.
 * 탭하면 화면 전체에 dim 이 깔리고 트리거 아래로 [WeekSegmentedControl] 패널이 펼쳐진다.
 * dim 영역(트리거 포함)을 탭하거나, 뒤로가기, 주차 선택 시 닫힌다.
 */
@Composable
fun WeekSelector(
    modifier: Modifier = Modifier,
    selectedWeek: WeekType,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onWeekSelected: (WeekType) -> Unit,
) {
    var anchorBottomPx by remember { mutableIntStateOf(0) }

    ONMITheme { color, typography ->
        Row(
            modifier = modifier
                .onGloballyPositioned { anchorBottomPx = it.boundsInWindow().bottom.roundToInt() }
                .clip(RoundedCornerShape(4.dp))
                .onmiClickable(onClick = { onExpandedChange(!expanded) })
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = selectedWeek.label,
                style = typography.Headline3,
                color = color.Black
            )
            DownIcon(
                tint = color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }

    if (expanded) {
        Popup(
            popupPositionProvider = FullWindowPositionProvider,
            properties = PopupProperties(focusable = true),
            onDismissRequest = { onExpandedChange(false) },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SelectorScrimColor)
                    .onmiClickable(
                        rippleEnabled = false,
                        onClick = { onExpandedChange(false) }
                    )
            ) {
                WeekSegmentedControl(
                    modifier = Modifier
                        .offset { IntOffset(x = 0, y = anchorBottomPx + SelectorPanelTopGap.roundToPx()) }
                        .fillMaxWidth()
                        .padding(horizontal = SelectorPanelHorizontalMargin),
                    selectedWeek = selectedWeek,
                    onWeekSelected = { week ->
                        onExpandedChange(false)
                        onWeekSelected(week)
                    },
                )
            }
        }
    }
}

/**
 * 저번 주 / 이번 주 / 다음 주 3분할 선택 UI.
 */
@Composable
fun WeekSegmentedControl(
    modifier: Modifier = Modifier,
    selectedWeek: WeekType,
    onWeekSelected: (WeekType) -> Unit,
) {
    ONMITheme { color, typography ->
        Row(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color.White)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            WeekType.entries.forEach { week ->
                val isSelected = week == selectedWeek

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (isSelected) color.Black else color.White)
                        .onmiClickable(onClick = { onWeekSelected(week) })
                        .padding(vertical = 17.5.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = week.label,
                        style = typography.Body3,
                        color = if (isSelected) color.White else color.TextPrimary
                    )
                }
            }
        }
    }
}

/**
 * Popup 을 윈도우 원점에 붙여 화면 전체를 덮게 한다. 패널 위치는 콘텐츠 쪽에서 offset 으로 잡는다.
 */
private object FullWindowPositionProvider : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset = IntOffset.Zero
}

@PreviewLightDark
@Composable
private fun WeekSelectorPre() {
    var selectedWeek by remember { mutableStateOf(WeekType.THIS_WEEK) }
    var expanded by remember { mutableStateOf(false) }

    ONMITheme { color, _ ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color.BackgroundMain)
                .padding(16.dp)
        ) {
            WeekSelector(
                selectedWeek = selectedWeek,
                expanded = expanded,
                onExpandedChange = { expanded = it },
                onWeekSelected = { selectedWeek = it },
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun WeekSegmentedControlPre() {
    var selectedWeek by remember { mutableStateOf(WeekType.NEXT_WEEK) }

    ONMITheme { color, _ ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color.Black)
                .padding(16.dp)
        ) {
            WeekSegmentedControl(
                selectedWeek = selectedWeek,
                onWeekSelected = { selectedWeek = it },
            )
        }
    }
}
