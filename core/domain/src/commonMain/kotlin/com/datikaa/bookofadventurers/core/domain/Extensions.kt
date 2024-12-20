package com.datikaa.bookofadventurers.core.domain

fun List<Modifier>.flatten(): List<Modifier> {
    val result = ArrayList<Modifier>()
    forEach { modifier ->
        result.add(modifier)
        if (modifier.nestedModifiers.isNotEmpty()) {
            result.addAll(modifier.nestedModifiers.flatten())
        }
    }
    return result
}

fun <T> concatenate(vararg lists: List<T>): List<T> {
    return listOf(*lists).flatten()
}
