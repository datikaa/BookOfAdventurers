package com.datikaa.bookofadventurers.core.database.prefill

import android.content.Context
import androidx.annotation.RawRes
import org.json.JSONArray
import java.io.BufferedReader

internal fun loadJsonArray(
        context: Context,
        @RawRes rawResourceId: Int,
): JSONArray {

    val inputStream = context.resources.openRawResource(rawResourceId)

    BufferedReader(inputStream.reader()).use {
        return JSONArray(it.readText())
    }
}