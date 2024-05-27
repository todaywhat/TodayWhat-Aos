package com.onmi.widget.glance.timetable

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStream
import java.io.OutputStream

object TimeTableInfoStateDefinition : GlanceStateDefinition<TimeTableInfo> {

    private const val DATA_STORE_FILENAME = "TimeTableInfo"

    private val Context.datastore by dataStore(DATA_STORE_FILENAME, TimeTableInfoSerializer)
    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<TimeTableInfo> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(DATA_STORE_FILENAME)
    }

    object TimeTableInfoSerializer : Serializer<TimeTableInfo> {
        override val defaultValue = TimeTableInfo.Loading

        override suspend fun readFrom(input: InputStream): TimeTableInfo = try {
            Json.decodeFromString(
                TimeTableInfo.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("Could not read data: ${exception.message}")
        }

        override suspend fun writeTo(t: TimeTableInfo, output: OutputStream) {
            output.use {
                it.write(
                    Json.encodeToString(TimeTableInfo.serializer(), t).encodeToByteArray()
                )
            }
        }
    }
}