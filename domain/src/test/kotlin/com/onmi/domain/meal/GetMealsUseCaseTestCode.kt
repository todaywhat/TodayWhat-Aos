package com.onmi.domain.meal

import com.onmi.database.UserDao
import com.onmi.database.UserEntity
import com.onmi.domain.repository.MealRepository
import com.onmi.domain.usecase.meal.GetMealsUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class GetMealsUseCaseTestCode : BehaviorSpec({
    val mockRepository = mockk<MealRepository>()
    val mockUserDao = mockk<UserDao>()

    val useCase = GetMealsUseCase(
        repository = mockRepository,
        userDao = mockUserDao
    )

    val today = LocalDate.of(2024, 10, 16) // 임의로 날짜 설정 (평일)
    val nextDay = LocalDate.of(2024, 10, 17)
    val saturday = LocalDate.of(2024, 10, 19) // 주말
    val nextMonday = LocalDate.of(2024, 10, 21) // 주말 이후 월요일

    Given("정보 기입을 완료한 사용자가") {
        When("메인 페이지에 진입한 경우") {
            And("저녁 이후 다음날 정보 표시 옵션만 켜져있다면") {
                coEvery { mockUserDao.getUserInfo() } returns flowOf(
                    UserEntity(
                        schoolType = "",
                        isSkipWeekend = false,
                        isShowNextDayInfoAfterDinner = true,
                    )
                )

                Then("7시 이전이라면 당일의 정보를 표시한다.") {
                    every { LocalTime.now() } returns LocalTime.of(6, 59)
                }
                Then("7시 이후라면 다음날 정보를 표시한다.") {
                    every { LocalTime.now() } returns LocalTime.of(7, 1)
                }
            }
            And("주말 건너뛰기 옵션만 켜져있다면") {
                coEvery { mockUserDao.getUserInfo() } returns flowOf(
                    UserEntity(
                        schoolType = "",
                        isSkipWeekend = true,
                        isShowNextDayInfoAfterDinner = false,
                    )
                )

                Then("주말이 아니라면 당일의 정보를 표시한다.") {
                    every { LocalDate.now().dayOfWeek } returns DayOfWeek.MONDAY
                }
                Then("주말이라면 다음주 월요일의 정보를 표시한다.") {
                    every { LocalDate.now().dayOfWeek } returns DayOfWeek.SATURDAY
                }
            }
            And("주말 건너뛰기, 저녁 이후 다음날 정보 표시 두 옵션이 모두 켜져있다면") {
                coEvery { mockUserDao.getUserInfo() } returns flowOf(
                    UserEntity(
                        schoolType = "",
                        isSkipWeekend = true,
                        isShowNextDayInfoAfterDinner = true,
                    )
                )

                Then("주말이고, 7시 이후여도 월요일의 정보가 표시되어야한다.") {
                    every { LocalDate.now().dayOfWeek } returns DayOfWeek.SATURDAY
                    every { LocalTime.now() } returns LocalTime.of(7, 1)
                }
            }
            And("모든 옵션이 꺼져있다면") {
                coEvery { mockUserDao.getUserInfo() } returns flowOf(
                    UserEntity(
                        schoolType = "",
                        isSkipWeekend = false,
                        isShowNextDayInfoAfterDinner = false,
                    )
                )

                Then("7시 이후여도 당일의 정보를 표시한다.") {
                    every { LocalTime.now() } returns LocalTime.of(7, 1)
                }
                Then("주말이라면 당일의 정보를 표시한다.") {
                    every { LocalDate.now().dayOfWeek } returns DayOfWeek.SATURDAY
                }
            }
        }
    }
})