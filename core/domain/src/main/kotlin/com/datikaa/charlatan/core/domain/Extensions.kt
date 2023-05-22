package com.datikaa.charlatan.core.domain
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
