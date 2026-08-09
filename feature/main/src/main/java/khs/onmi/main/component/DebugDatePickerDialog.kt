package khs.onmi.main.component

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable

/**
 * 디버그 빌드 전용 날짜 선택 다이얼로그.
 * 여기서 고른 날짜는 메인 화면의 급식·시간표 조회에 강제로 적용된다.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DebugDatePickerDialog(
    initialSelectedDateMillis: Long?,
    onDateSelected: (utcMillis: Long) -> Unit,
    onReset: () -> Unit,
    onDismiss: () -> Unit,
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialSelectedDateMillis
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { datePickerState.selectedDateMillis?.let(onDateSelected) },
                enabled = datePickerState.selectedDateMillis != null
            ) {
                Text(text = "확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onReset) {
                Text(text = "오늘로 초기화")
            }
            TextButton(onClick = onDismiss) {
                Text(text = "취소")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
