package com.datikaa.bookofadventurers.core.database.prefill

import androidx.room.RoomDatabase
import androidx.room.deferredTransaction
import androidx.room.immediateTransaction
import androidx.room.useWriterConnection
import androidx.sqlite.SQLiteConnection
import com.datikaa.bookofadventurers.Res
import com.datikaa.bookofadventurers.core.database.BoaDatabase
import com.datikaa.bookofadventurers.core.database.crossref.BackgroundSkillProficiencyCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.ClassSavingThrowCrossRef
import com.datikaa.bookofadventurers.core.database.crossref.ClassSkillProficiencyCrossRef
import com.datikaa.bookofadventurers.core.database.entity.BackgroundEntity
import com.datikaa.bookofadventurers.core.database.entity.ClassEntity
import com.datikaa.bookofadventurers.core.database.entity.ModifierEntity
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RoomPrefiller(
    private val jsonResourceFunctions: JsonResourceFunctions,
) : RoomDatabase.Callback(), KoinComponent {

    private val boaDatabase by inject<BoaDatabase>()

    override fun onCreate(connection: SQLiteConnection): Unit = with(boaDatabase) {
        super.onCreate(connection)
        prefill()
    }

    override fun onDestructiveMigration(db: SQLiteConnection): Unit = with(boaDatabase) {
        super.onDestructiveMigration(db)
        prefill()
    }

    private fun prefill(): Unit = with(boaDatabase) {
        getCoroutineScope().launch {
            useWriterConnection { transactor ->
                transactor.deferredTransaction {
                    fillWithStartingModifiers()
                    fillWithClasses()
                    fillWithBackgrounds()
                }
            }
        }
    }

    private suspend fun fillWithStartingModifiers() {
        val modifiersRaw = jsonResourceFunctions.loadJsonArray(Res.files.proficiency_modifiers_json)
        val modifiers = Json.decodeFromString<List<Modifier>>(modifiersRaw)

        modifiers.forEach { modifier ->
            val modifierEntity = ModifierEntity(
                id = 0,
                parentModifierId = null,
                name = modifier.name,
                description = modifier.description,
                type = modifier.toType(),
                modifiableScoreType = modifier.toModifiableScoreType(),
                modifierValue = modifier.modifierValue,
            )
            boaDatabase.modifierDao().insertModifier(modifierEntity)
        }
    }

    private suspend fun fillWithClasses() {
        val classDao = boaDatabase.classDao()
        val classesRaw = jsonResourceFunctions.loadJsonArray(Res.files.classes_json)
        val classes = Json.decodeFromString<List<Class>>(classesRaw)

        classes.forEach { clazz ->
            val classEntity = ClassEntity(
                id = 0,
                name = clazz.name,
                selectableSkillCount = clazz.selectableSkillCount,
            )
            val classId = classDao.insertClass(classEntity)
            clazz.savingThrows.forEach { savingThrow ->
                val classSavingThrowsCrossRef = ClassSavingThrowCrossRef(
                    classId = classId,
                    modifierId = savingThrow.toLong(),
                )
                classDao.insertClassSavingThrowCrossRef(classSavingThrowsCrossRef)
            }

            val skills = clazz.skills
            skills.forEach { skill ->
                val classSkillProficiencyCrossRef = ClassSkillProficiencyCrossRef(
                    classId = classId,
                    modifierId = skill.toLong(),
                )
                classDao.insertClassSkillProficiencyCrossRef(classSkillProficiencyCrossRef)
            }
        }
    }

    private suspend fun fillWithBackgrounds() {
        val backgroundDao = boaDatabase.backgroundDao()
        val backgroundsRaw = jsonResourceFunctions.loadJsonArray(Res.files.backgrounds_json)
        val backgrounds = Json.decodeFromString<List<Background>>(backgroundsRaw)

        backgrounds.forEach { background ->
            val backgroundEntity = BackgroundEntity(
                id = 0,
                name = background.name,
                featureTitle = background.featureTitle,
                featureDescription = background.featureDescription,
                suggestedCharacteristics = background.suggestedCharacteristics,
            )
            val backgroundId = backgroundDao.insertBackground(backgroundEntity)
            val skillProficiencies = background.skillProficiencies
            skillProficiencies.forEach { skillProficiency ->
                val classSavingThrowsCrossRef = BackgroundSkillProficiencyCrossRef(
                    backgroundId = backgroundId,
                    modifierId = skillProficiency.toLong(),
                )
                backgroundDao.insertBackgroundSkillProficiencyCrossRef(classSavingThrowsCrossRef)
            }
        }
    }

    private fun Modifier.toType(): ModifierEntity.Type =
        ModifierEntity.Type.entries.firstOrNull {
            it.name == type
        } ?: throw IllegalStateException("Type $this is unknown.")

    private fun Modifier.toModifiableScoreType(): ModifierEntity.ModifiableScoreType =
        ModifierEntity.ModifiableScoreType.entries.firstOrNull {
            it.name == modifiableScoreType
        } ?: throw IllegalStateException("Type $this is unknown.")
}
