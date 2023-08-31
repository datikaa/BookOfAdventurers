package com.bookofadventurers.feature.wizard.domain

import com.datikaa.bookofadventurers.core.data.ClassesRepository
import com.datikaa.bookofadventurers.core.domain.BoaClass

class GetListOfClassesUseCase(
    private val classesRepository: ClassesRepository,
) {

    suspend operator fun invoke(): List<BoaClass> = classesRepository.getAllClasses()
}