package com.datikaa.bookofadventurers.launcher.domain

import com.datikaa.bookofadventurers.core.data.ClassesRepository

class FlowAllClassesUseCase(
    private val classesRepository: ClassesRepository,
) {

    operator fun invoke() = classesRepository.flowAllClasses()
}
