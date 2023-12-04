package io.kl3jvi.utils

import io.kl3jvi.models.ProjectCreation
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DataSerializer : KSerializer<Any?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Any", PrimitiveKind.STRING)

    override fun serialize(
        encoder: Encoder,
        value: Any?,
    ) {
        when (value) {
            is String -> encoder.encodeString(value)
            is Int -> encoder.encodeInt(value)
            is Long -> encoder.encodeLong(value)
            is Double -> encoder.encodeDouble(value)
            is Boolean -> encoder.encodeBoolean(value)
            is Float -> encoder.encodeFloat(value)
            is Short -> encoder.encodeShort(value)
            is Byte -> encoder.encodeByte(value)
            is Char -> encoder.encodeChar(value)
            is ProjectCreation -> encoder.encodeString(value.toString())
            is List<*> -> {
                if (value.isNotEmpty() && value[0] is ProjectCreation) {
                    encoder.encodeSerializableValue(
                        ListSerializer(ProjectCreation.serializer()),
                        value as List<ProjectCreation>,
                    )
                }
            }

            else -> encoder.encodeNull()
        }
    }

    override fun deserialize(decoder: Decoder): Any? {
        throw NotImplementedError("Deserialization is not supported")
    }
}
