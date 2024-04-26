package com.onmi.widget.timetable

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.onmi.widget.R

class TimeTableRemoteViewsFactory(
    private val context: Context
) : RemoteViewsService.RemoteViewsFactory {

    private var data = listOf(
        "일본어",
        "일본어",
        "웹 프로그래밍 실무",
        "웹 프로그래밍 실무",
        "인공지능 프로그래밍",
        "인공지능 프로그래밍",
        "스마트문화앱 시스템 설계"
    )

    override fun onCreate() {}

    override fun onDataSetChanged() {}

    override fun onDestroy() {}

    override fun getCount(): Int = data.size

    override fun getViewAt(position: Int): RemoteViews {
        val listviewWidget =
            RemoteViews(context.packageName, R.layout.time_table_widget_list_item_small)

        listviewWidget.run {
            setTextViewText(R.id.subjectTime, "${position + 1}")
            setTextViewText(R.id.subjectName, data[position])
        }

        return listviewWidget
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}