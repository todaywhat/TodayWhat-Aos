package khs.onmi.core.common.android

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase

private val firebaseAnalytics: FirebaseAnalytics by lazy { Firebase.analytics }

enum class Screen(val value: String) {
    MAIN("main_page"),
    NOTICE("notice_page"),
    SETTING("setting_page"),
    SCHOOL_SETTING("school_setting_page"),
    MODIFY_TIME_TABLE("modify_time_table_page"),
}

enum class InquiryType(val value: String) {
    MAIL("mail"),
    GITHUB("github"),
}

enum class SelectedType(val value: String) {
    SWIPED("swiped"),
    TAPPED("tapped"),
}

enum class SchoolSettingStep(val value: String) {
    SCHOOL("school"),
    GRADE("grade"),
    CLASS("class"),
    MAJOR("major"),
}

enum class Weekday(val value: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일")
}

enum class Allergy(val value: String) {
    EGG("난류"),
    MILK("우유"),
    BUCKWHEAT("메밀"),
    PEANUT("땅콩"),
    SOYBEAN("대두"),
    WHEAT("밀"),
    MACKEREL("고등어"),
    CRAB("게"),
    SHRIMP("새우"),
    PORK("돼지고기"),
    PEACH("복숭아"),
    TOMATO("토마토"),
    SULFITE("아황산염"),
    WALNUT("호두"),
    CHICKEN("닭고기"),
    BEEF("쇠고기"),
    SQUID("오징어"),
    SHELLFISH("조개류");
}

enum class WidgetFamily(val value: String) {
    SYSTEM_SMALL("systemSmall"),
    SYSTEM_MEDIUM("systemMedium"),
    SYSTEM_LARGE("systemLarge"),
    SYSTEM_EXTRA_LARGE("systemExtraLarge"),
    ACCESSORY_CIRCULAR("accessoryCircular"),
    ACCESSORY_RECTANGULAR("accessoryRectangular"),
}

enum class WidgetKind(val value: String) {
    MEAL("TodayWhatMealWidget"),
    TIMETABLE("TodayWhatTimeWidget"),
    MEAL_TIMETABLE("TodayWhatMealTimetableWidget"),
}

object EventLogger {

    fun setUserProperties(vararg properties: Pair<String, String>) {
        properties.forEach { property ->
            firebaseAnalytics.setUserProperty(property.first, property.second)
        }
    }

    fun pageShowed(screen: Screen) {
        firebaseAnalytics.logEvent("page_showed") {
            param("page_name", screen.value)
        }
    }

    fun selectInquiryType(inquiryType: InquiryType) {
        firebaseAnalytics.logEvent("select_inquiry_type") {
            param("type", inquiryType.value)
        }
    }

    fun clickNoticeButton() {
        firebaseAnalytics.logEvent("click_notice_button", null)
    }

    fun clickIsSkipWeekendToggle(isSkipWeekend: Boolean) {
        firebaseAnalytics.logEvent("click_is_skip_weekend_toggle") {
            param("is_skip_weekend", isSkipWeekend.toString())
        }
    }

    fun widgetConfiguration(family: WidgetFamily, kind: WidgetKind) {
        firebaseAnalytics.logEvent("widget_configuration") {
            param("family", family.value)
            param("kind", kind.value)
        }
    }

    fun clickIsOnModifiedTimeTableToggle(isOnModifiedTimeTable: Boolean) {
        firebaseAnalytics.logEvent("click_is_on_modified_time_table_toggle") {
            param("is_on_modified", isOnModifiedTimeTable.toString())
        }
    }

    fun selectedMealTab(selectedType: SelectedType) {
        firebaseAnalytics.logEvent("selected_meal_tab") {
            param("type", selectedType.value)
        }
    }

    fun clickIsSkipAfterDinnerToggle(isSkipAfterDinner: Boolean) {
        firebaseAnalytics.logEvent("click_is_skip_after_dinner_toggle") {
            param("is_skip_after_dinner", isSkipAfterDinner.toString())
        }
    }

    fun clickTutorialButton() {
        firebaseAnalytics.logEvent("click_tutorial_button", null)
    }

    fun clickSchoolSettingButton() {
        firebaseAnalytics.logEvent("click_school_setting_button", null)
    }

    fun selectTimeTableTab(selectedType: SelectedType) {
        firebaseAnalytics.logEvent("select_time_table_tab") {
            param("type", selectedType.value)
        }
    }

    fun clickAllergySettingButton() {
        firebaseAnalytics.logEvent("click_allergy_setting_button", null)
    }

    fun clickInquiryButton() {
        firebaseAnalytics.logEvent("click_inquiry_button", null)
    }

    fun clickSettingButton() {
        firebaseAnalytics.logEvent("click_setting_button", null)
    }

    fun clickModifyTimeTableButton() {
        firebaseAnalytics.logEvent("click_modify_time_table_button", null)
    }

    fun completeSchoolSettingStep(step: SchoolSettingStep) {
        firebaseAnalytics.logEvent("complete_school_setting_step") {
            param("step", step.value)
        }
    }

    fun completeModifyTimeTable(weekday: Weekday) {
        firebaseAnalytics.logEvent("complete_modify_time_table") {
            param("week", weekday.value)
        }
    }

    fun completeSettingAllergy(allergies: List<Allergy>) {
        firebaseAnalytics.logEvent("complete_setting_allergy") {
            param("allergies", allergies.joinToString { it.value })
        }
    }
}