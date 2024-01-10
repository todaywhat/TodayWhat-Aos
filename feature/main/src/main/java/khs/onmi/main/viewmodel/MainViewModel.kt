package khs.onmi.main.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import khs.onmi.main.viewmodel.container.MainSideEffect
import khs.onmi.main.viewmodel.container.MainState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ContainerHost<MainState, MainSideEffect>, ViewModel() {
    override val container = container<MainState, MainSideEffect>(
        MainState()
    )

    init {
        setDummyData()
    }

    private fun setDummyData() = intent {
        reduce {
            state.copy(
                schoolName = "광주소프트웨어마이스터고",
                grade = 3,
                `class` = 1,
                breakfast = Pair(listOf("김치", "파김치", "배추김치", "열무김치", "총각김치", "익은김치"), 123.4F),
                lunch = Pair(listOf("김치", "파김치", "배추김치", "열무김치", "총각김치", "익은김치"), 123.4F),
                dinner = Pair(listOf("김치", "파김치", "배추김치", "열무김치", "총각김치", "익은김치"), 123.4F),
                timetable = listOf("국어", "영어", "수학", "사회", "과학", "기술가정", "역사", "한문"),
            )
        }
    }
}