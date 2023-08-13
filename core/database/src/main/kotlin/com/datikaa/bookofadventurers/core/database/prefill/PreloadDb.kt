package com.datikaa.bookofadventurers.core.database.prefill

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.datikaa.bookofadventurers.core.database.BoaDatabase
import com.datikaa.bookofadventurers.core.database.R
import com.datikaa.bookofadventurers.core.database.crossref.ClassModifierCrossRef
import com.datikaa.bookofadventurers.core.database.entity.ClassEntity
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PreloadDb(private val context: Context) : RoomDatabase.Callback(), KoinComponent {

    private val db by inject<BoaDatabase>()

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingModifiers(context)
            fillWithClasses()
        }
    }

    private suspend fun fillWithStartingModifiers(context: Context) {
        try {
            val modifiers = loadJsonArray(context, R.raw.proficiency_modifiers)
            for (i in 0 until modifiers.length()) {
                val item = modifiers.getJSONObject(i)
                val modifierEntity = ModifierEntity(
                        id = 0,
                        parentModifierId = null,
                        name = item.getString("name"),
                        description = item.getString("description"),
                        type = item.toType(),
                        modifiableScoreType = item.toModifiableScoreType(),
                        modifierValue = item.getModifierValue(),
                )
                db.modifierDao().insertModifier(modifierEntity)
            }
        } catch (e: JSONException) {
            Log.e("PrefillBasicModifiers", "Prefill error", e)
        } catch (e: IllegalStateException) {
            Log.e("PrefillBasicModifiers", "Prefill error", e)
        }
    }

    private suspend fun fillWithClasses() {
        try {
            db.classDao().run {
                insertClass(
                    ClassEntity(
                        id = 0, name = "Wizard",
                    )
                )
                insertClassModifierCrossRef(
                    ClassModifierCrossRef(
                        classId = 1, modifierId = 1,
                    )
                )
                insertClassModifierCrossRef(
                    ClassModifierCrossRef(
                        classId = 1, modifierId = 3,
                    )
                )
            }
        } catch (e: JSONException) {
            Log.e("PrefillBasicModifiers", "Prefill error", e)
        } catch (e: IllegalStateException) {
            Log.e("PrefillBasicModifiers", "Prefill error", e)
        }
    }

    private fun JSONObject.toType(): ModifierEntity.Type = ModifierEntity.Type.values().firstOrNull {
        it.name == getString("type")
    } ?: throw IllegalStateException("Type $this is unknown.")

    private fun JSONObject.toModifiableScoreType(): ModifierEntity.ModifiableScoreType =
            ModifierEntity.ModifiableScoreType.values().firstOrNull {
                it.name == getString("modifiableScoreType")
            } ?: throw IllegalStateException("Type $this is unknown.")

    private fun JSONObject.getModifierValue(): Int? = if (has("modifierValue")) {
        getInt("modifierValue")
    } else null
}
