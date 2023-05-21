import kotlin.reflect.KClass

data class Modifier(
    val name: String,
    val description: String,
    val attributeModifiers: List<AttributeModifier>,
    val nestedModifiers: List<Modifier>,
) {
    data class AttributeModifier(
        val modifiableAttributeType: KClass<out ModifiableAttribute>,
        val value: Int,
    )
}

fun List<Modifier.AttributeModifier>.filterModifiableAttributeType(
    modifiableAttributeType: KClass<out ModifiableAttribute>
) = filter { it.modifiableAttributeType == modifiableAttributeType }