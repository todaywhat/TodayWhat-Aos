package com.onmi.widget.meal

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.onmi.widget.R

class MealRemoteViewsFactory(
    private val context: Context
) : RemoteViewsService.RemoteViewsFactory {

    private var data = listOf(
        "친환경백미찹쌀밥",
        "매콤어묵우국",
        "청포묵무침",
        "닭갈비",
        "치즈소떡소떡&양념소스",
        "상큼이주스",
        "닭가슴살양상추샐러드&오이셀러개맛없어"
    )

    override fun onCreate() {}

    override fun onDataSetChanged() {}

    override fun onDestroy() {}

    override fun getCount(): Int = data.size

    override fun getViewAt(position: Int): RemoteViews {
        val listviewWidget =
            RemoteViews(context.packageName, R.layout.small_meal_widget_list_item)

        listviewWidget.run {
            setTextViewText(R.id.mealName, data[position])
        }

        return listviewWidget
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = 0

    override fun hasStableIds(): Boolean = false
}