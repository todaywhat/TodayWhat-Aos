package khs.onmi.enterinformation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import khs.onmi.core.designsystem.theme.ONMITheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentSelectorBottomSheet(
    departments: List<String>,
    sheetState: SheetState,
    selectedItemIdx: Int,
    onItemClick: (idx: Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    ONMITheme { color, typography ->
        ModalBottomSheet(
            containerColor = color.BackgroundMain,
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            dragHandle = null,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "학과선택",
                    style = typography.Headline2,
                    color = color.Black
                )
                LazyColumn(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                ) {
                    itemsIndexed(departments) { idx, item ->
                        DepartmentSelectorItem(
                            department = item,
                            isSelected = selectedItemIdx == idx,
                            onItemClick = {
                                onItemClick(idx)
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DepartmentSelectorBottomSheetPre() {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val (selectedItemIdx, setSelectedItemIdx) = remember {
        mutableStateOf(-1)
    }
    val (visible, setVisible) = remember {
        mutableStateOf(false)
    }

    val dummy = listOf(
        "없음",
        "SW 개발과",
        "임베디드 개발과",
        "스마트IoT과",
        "임베디드SW과",
        "e-비즈니스과",
    )

    Button(
        onClick = {
            setVisible(true)
        }
    ) {
        Text("open")
    }

    if (visible) {
        DepartmentSelectorBottomSheet(
            departments = dummy,
            sheetState = sheetState,
            selectedItemIdx = selectedItemIdx,
            onItemClick = { idx ->
                setSelectedItemIdx(idx)
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) setVisible(false)
                }
            },
            onDismissRequest = {
                setVisible(false)
            }
        )
    }
}
        

