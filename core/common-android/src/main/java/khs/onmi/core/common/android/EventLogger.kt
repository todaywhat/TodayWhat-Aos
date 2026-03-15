package khs.onmi.core.common.android

import android.content.Context
import com.amplitude.android.Amplitude
import com.amplitude.android.Configuration
import com.amplitude.android.plugins.SessionReplayPlugin
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.ktx.Firebase
import java.util.UUID

private val firebaseAnalytics: FirebaseAnalytics by lazy { Firebase.analytics }

private const val PREFS_NAME = "onmi_event_logger"
private const val KEY_USER_ID = "amplitude_user_id"

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

enum class WidgetType(val value: String) {
    MEAL_SMALL("meal_small"),
    MEAL_MEDIUM("meal_medium"),
    MEAL_LARGE("meal_large"),
    TIMETABLE_SMALL("timetable_small"),
    TIMETABLE_MEDIUM("timetable_medium"),
    TIMETABLE_LARGE("timetable_large"),
    MEAL_AND_TIMETABLE_MEDIUM("meal_and_timetable_medium"),
}

object EventLogger {

    private var amplitude: Amplitude? = null

    fun init(context: Context, amplitudeApiKey: String) {
        if (amplitudeApiKey.isBlank()) return

        val appContext = context.applicationContext

        amplitude = Amplitude(
            Configuration(
                apiKey = amplitudeApiKey,
                context = appContext,
            )
        ).apply {
            add(SessionReplayPlugin(sampleRate = 0.03))
        }

        val userId = getOrCreateUserId(appContext)
        amplitude?.setUserId(userId)
    }

    private fun getOrCreateUserId(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val existingId = prefs.getString(KEY_USER_ID, null)
        if (existingId != null) return existingId

        val newId = UUID.randomUUID().toString()
        prefs.edit().putString(KEY_USER_ID, newId).apply()
        return newId
    }

    fun setUserProperties(vararg properties: Pair<String, Any>) {
        properties.forEach { (key, value) ->
            firebaseAnalytics.setUserProperty(key, value.toString())
        }

        amplitude?.let { amp ->
            val identify = com.amplitude.android.events.Identify()
            properties.forEach { (key, value) ->
                when (value) {
                    is String -> identify.set(key, value)
                    is Int -> identify.set(key, value)
                    is Long -> identify.set(key, value)
                    is Double -> identify.set(key, value)
                    is Boolean -> identify.set(key, value)
                    is Array<*> -> identify.set(key, value.toList().filterNotNull())
                    is List<*> -> identify.set(key, value.filterNotNull())
                    else -> identify.set(key, value.toString())
                }
            }
            amp.identify(identify)
        }
    }

    fun pageShowed(screen: Screen) {
        firebaseAnalytics.logEvent("page_showed") {
            param("page_name", screen.value)
        }
        amplitude?.track("page_showed", mapOf("page_name" to screen.value))
    }

    fun selectInquiryType(inquiryType: InquiryType) {
        firebaseAnalytics.logEvent("select_inquiry_type") {
            param("type", inquiryType.value)
        }
        amplitude?.track("select_inquiry_type", mapOf("type" to inquiryType.value))
    }

    fun clickNoticeButton() {
        firebaseAnalytics.logEvent("click_notice_button", null)
        amplitude?.track("click_notice_button")
    }

    fun clickIsSkipWeekendToggle(isSkipWeekend: Boolean) {
        firebaseAnalytics.logEvent("click_is_skip_weekend_toggle") {
            param("is_skip_weekend", isSkipWeekend.toString())
        }
        amplitude?.track("click_is_skip_weekend_toggle", mapOf("is_skip_weekend" to isSkipWeekend))
    }

    fun clickIsOnModifiedTimeTableToggle(isOnModifiedTimeTable: Boolean) {
        firebaseAnalytics.logEvent("click_is_on_modified_time_table_toggle") {
            param("is_on_modified", isOnModifiedTimeTable.toString())
        }
        amplitude?.track("click_is_on_modified_time_table_toggle", mapOf("is_on_modified" to isOnModifiedTimeTable))
    }

    fun selectedMealTab(selectedType: SelectedType) {
        firebaseAnalytics.logEvent("selected_meal_tab") {
            param("type", selectedType.value)
        }
        amplitude?.track("selected_meal_tab", mapOf("type" to selectedType.value))
    }

    fun clickIsSkipAfterDinnerToggle(isSkipAfterDinner: Boolean) {
        firebaseAnalytics.logEvent("click_is_skip_after_dinner_toggle") {
            param("is_skip_after_dinner", isSkipAfterDinner.toString())
        }
        amplitude?.track("click_is_skip_after_dinner_toggle", mapOf("is_skip_after_dinner" to isSkipAfterDinner))
    }

    fun clickTutorialButton() {
        firebaseAnalytics.logEvent("click_tutorial_button", null)
        amplitude?.track("click_tutorial_button")
    }

    fun clickSchoolSettingButton() {
        firebaseAnalytics.logEvent("click_school_setting_button", null)
        amplitude?.track("click_school_setting_button")
    }

    fun selectTimeTableTab(selectedType: SelectedType) {
        firebaseAnalytics.logEvent("select_time_table_tab") {
            param("type", selectedType.value)
        }
        amplitude?.track("select_time_table_tab", mapOf("type" to selectedType.value))
    }

    fun clickAllergySettingButton() {
        firebaseAnalytics.logEvent("click_allergy_setting_button", null)
        amplitude?.track("click_allergy_setting_button")
    }

    fun clickInquiryButton() {
        firebaseAnalytics.logEvent("click_inquiry_button", null)
        amplitude?.track("click_inquiry_button")
    }

    fun clickSettingButton() {
        firebaseAnalytics.logEvent("click_setting_button", null)
        amplitude?.track("click_setting_button")
    }

    fun clickModifyTimeTableButton() {
        firebaseAnalytics.logEvent("click_modify_time_table_button", null)
        amplitude?.track("click_modify_time_table_button")
    }

    fun completeSchoolSettingStep(step: SchoolSettingStep) {
        firebaseAnalytics.logEvent("complete_school_setting_step") {
            param("step", step.value)
        }
        amplitude?.track("complete_school_setting_step", mapOf("step" to step.value))
    }

    fun clickNoticeItem(noticeId: String) {
        firebaseAnalytics.logEvent("click_notice_item") {
            param("notice_id", noticeId)
        }
        amplitude?.track("click_notice_item", mapOf("notice_id" to noticeId))
    }

    fun completeModifyTimeTable(weekday: Weekday) {
        firebaseAnalytics.logEvent("complete_modify_time_table") {
            param("week", weekday.value)
        }
        amplitude?.track("complete_modify_time_table", mapOf("week" to weekday.value))
    }

    fun completeSettingAllergy(allergies: List<Allergy>) {
        firebaseAnalytics.logEvent("complete_setting_allergy") {
            param("allergies", allergies.joinToString { it.value })
        }
        amplitude?.track("complete_setting_allergy", mapOf("allergies" to allergies.map { it.value }))
    }

    /**
     * 인앱 위젯 추가 화면에서 위젯 하나 클릭 후, '홈 화면에 추가' 버튼을 클릭
     */
    fun completeAddToWidget(widgetType: String) {
        firebaseAnalytics.logEvent("complete_add_to_widget") {
            param("widget", widgetType)
        }
        amplitude?.track("complete_add_to_widget", mapOf("widget" to widgetType))
    }

    /**
     * 인앱 위젯 추가 화면에서 위젯 하나를 눌렀을 때
     */
    fun clickAddToWidgetType(widget: WidgetType) {
        firebaseAnalytics.logEvent("click_add_to_widget_type") {
            param("widget", widget.value)
        }
        amplitude?.track("click_add_to_widget_type", mapOf("widget" to widget.value))
    }

    /**
     * 설정화면에서 위젯 추가 버튼을 클릭함
     */
    fun clickAddToWidget() {
        firebaseAnalytics.logEvent("click_add_to_widget", null)
        amplitude?.track("click_add_to_widget")
    }
}
