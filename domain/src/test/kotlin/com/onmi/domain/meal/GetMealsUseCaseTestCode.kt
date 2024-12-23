package com.onmi.domain.meal

import com.onmi.database.UserDao
import com.onmi.domain.repository.MealRepository
import com.onmi.domain.usecase.meal.GetMealsUseCase
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk

class GetMealsUseCaseTestCode : BehaviorSpec({
    val mockRepository = mockk<MealRepository>()
    val mockUserDao = mockk<UserDao>()

    val useCase = GetMealsUseCase(
        repository = mockRepository,
        userDao = mockUserDao
    )

    Given("정보 기입을 완료한 사용자가") {
        When("메인 페이지에 진입한 경우") {
            And("저녁 이후 다음날 정보 표시 옵션만 켜져있다면") {
                Then("7시 이전이라면 당일의 정보를 표시한다.") {

                }

                Then("7시 이후라면 다음날 정보를 표시한다.") {

                }
            }

            And("주말 건너뛰기 옵션만 켜져있다면") {
                Then("주말이 아니라면 당일의 정보를 표시한다.") {

                }

                Then("주말이라면 다음주 월요일의 정보를 표시한다.") {

                }
            }

            And("주말 건너뛰기, 저녁 이후 다음날 정보 표시 두 옵션이 모두 켜져있다면") {
                Then("주말이고, 7시 이후여도 월요일의 정보가 표시되어야한다.") {

                }
            }

            And("모든 옵션이 꺼져있다면") {
                Then("7시 이후여도 당일의 정보를 표시한다.") {

                }

                Then("주말이라면 당일의 정보를 표시한다.") {

                }
            }
        }
    }
})