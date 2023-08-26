package com.datikaa.bookofadventurers.core.database.prefill

import android.content.Context
import android.util.Log
import com.datikaa.bookofadventurers.core.database.R
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity
import com.datikaa.bookofadventurers.core.database.realm.RealmPlayerClass
import com.datikaa.bookofadventurers.core.database.realm.RealmProficiencyModifier
import com.datikaa.bookofadventurers.core.database.realm.RealmScoreModifier
import io.realm.kotlin.InitialDataCallback
import io.realm.kotlin.MutableRealm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmObject
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.atomic.AtomicInteger

class PreloadDb(private val context: Context) : InitialDataCallback {

    override fun MutableRealm.write() {
        fillWithStartingModifiers()
        fillWithClasses()
    }

    private fun MutableRealm.fillWithStartingModifiers() {
        val modifierId = AtomicInteger(0)
        try {
            val modifiers = loadJsonArray(context, R.raw.proficiency_modifiers)
            for (i in 0 until modifiers.length()) {
                val item = modifiers.getJSONObject(i)
                val realmObj: RealmObject = when (item.toType()) {
                    ModifierEntity.Type.Holder -> {
                        throw IllegalArgumentException("Deprecated type")
                    }

                    ModifierEntity.Type.Score -> RealmScoreModifier().apply {
                        _id = modifierId.getAndIncrement()
                        name = item.getString("name")
                        description = item.getString("description")
                        modifiableScoreType = item.toModifiableScoreType().ordinal
                        modifierValue = item.getModifierValue()
                            ?: throw IllegalArgumentException("Score modifier must have value")
                    }

                    ModifierEntity.Type.Proficiency -> RealmProficiencyModifier().apply {
                        _id = modifierId.getAndIncrement()
                        name = item.getString("name")
                        description = item.getString("description")
                        proficiencyType = item.toModifiableScoreType().ordinal
                    }
                }
                copyToRealm(realmObj)
            }
        } catch (e: JSONException) {
            Log.e("PrefillBasicModifiers", "Prefill error", e)
        } catch (e: IllegalStateException) {
            Log.e("PrefillBasicModifiers", "Prefill error", e)
        }
    }

    private fun MutableRealm.fillWithClasses() {
        val classId = AtomicInteger(0)
        try {
            val classes = loadJsonArray(context, R.raw.classes)
            for (i in 0 until classes.length()) {
                val item = classes.getJSONObject(i)
                val modifierIds = mutableListOf<Int>()
                val classModifiers = item.getJSONArray("modifiers")
                for (j in 0 until classModifiers.length()) {
                    modifierIds.add(classModifiers.getInt(j))
                }
                val realmProficiencyModifiers = query<RealmProficiencyModifier>().find()
                val realmObj = RealmPlayerClass().apply {
                    _id = classId.getAndIncrement()
                    name = item.getString("name")
                    proficiencyModifiers = realmProficiencyModifiers.filter {
                        modifierIds.contains(it._id)
                    }.toRealmList()
                }
                copyToRealm(realmObj)
            }
        } catch (e: JSONException) {
            Log.e("PrefillBasicModifiers", "Prefill error", e)
        } catch (e: IllegalStateException) {
            Log.e("PrefillBasicModifiers", "Prefill error", e)
        }
    }

    private fun JSONObject.toType(): ModifierEntity.Type =
        ModifierEntity.Type.entries.firstOrNull {
            it.name == getString("type")
        } ?: throw IllegalStateException("Type $this is unknown.")

    private fun JSONObject.toModifiableScoreType(): ModifierEntity.ModifiableScoreType =
        ModifierEntity.ModifiableScoreType.entries.firstOrNull {
            it.name == getString("modifiableScoreType")
        } ?: throw IllegalStateException("Type $this is unknown.")

    private fun JSONObject.getModifierValue(): Int? = if (has("modifierValue")) {
        getInt("modifierValue")
    } else null
}
