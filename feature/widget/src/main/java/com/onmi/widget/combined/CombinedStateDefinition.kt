package com.onmi.widget.combined

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

object CombinedStateDefinition : GlanceStateDefinition<CombinedInfo> {

    private const val DATA_STORE_FILENAME = "CombinedInfo"

    private val Context.datastore by dataStore(DATA_STORE_FILENAME, CombinedInfoSerializer)
    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<CombinedInfo> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(DATA_STORE_FILENAME)
    }

    object CombinedInfoSerializer : Serializer<CombinedInfo> {
        override val defaultValue = CombinedInfo.Loading

        override suspend fun readFrom(input: InputStream): CombinedInfo = try {
            Json.decodeFromString(
                CombinedInfo.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("Could not read data: ${exception.message}")
        }

        override suspend fun writeTo(t: CombinedInfo, output: OutputStream) {
            output.use {
                it.write(
                    Json.encodeToString(CombinedInfo.serializer(), t).encodeToByteArray()
                )
            }
        }
    }
}