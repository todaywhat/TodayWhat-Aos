package com.onmi.widget.combined

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.glance.state.GlanceStateDefinition
import java.io.File

object CombinedStateDefinition : GlanceStateDefinition<CombinedInfo> {
    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<CombinedInfo> {
        TODO("Not yet implemented")
    }

    override fun getLocation(context: Context, fileKey: String): File {
        TODO("Not yet implemented")
    }
}