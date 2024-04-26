package com.onmi.widget.timetable

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import com.onmi.widget.R

class TimeTableWidgetProvider : AppWidgetProvider() {

    // 최초의 앱 위젯 등록시 호출 (각 앱 위젯 인스턴스가 등록 될때마다 호출 되는것 X)
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    // 마지막 최종 앱 위젯 인스턴스가 삭제 될 때 호출
    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    // 앱 위젯이 등록 될 때, 앱 위젯의 크기가 변경 될 때 호출됨
    // Bundle 에 위젯 너비/높이의 상한값/하한값 정보를 넘겨주며 이를 통해 컨텐츠를 표시하거나 숨기는 등의 동작을 구현
    override fun onAppWidgetOptionsChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        newOptions: Bundle
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    // 위젯이 업데이트 될 때 호출, 위젯 메타데이터 xml에서 updatePeriodMillis로 업데이트 주기를 설정할 수 있음
    // 위젯이 추가될 때에도 호출됨
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val widget = ComponentName(context, TimeTableWidgetProvider::class.java)

        appWidgetIds.forEach { _ ->
            val serviceIntent = Intent(context, TimeTableWidgetRemoteViewsService::class.java)
            val views = RemoteViews(context.packageName, R.layout.time_table_widget_small)
            views.setRemoteAdapter(R.id.timeTableSmallList, serviceIntent)

            appWidgetManager.updateAppWidget(widget, views)
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    // 앱 데이터가 구글 시스템에 백업 된 이후 복원 될 때 호출됨.
    // 일반적으로는 별로 사용되지 않음
    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
    }

    // 위젯이 삭제 될 때
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }
}