package io.kl3jvi.utils

import io.kl3jvi.models.Project
import io.kl3jvi.models.ProjectCreation
import io.kl3jvi.models.User
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DataSerializer : KSerializer<Any?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Any", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Any?) {
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
            is ProjectCreation -> encoder.encodeSerializableValue(ProjectCreation.serializer(), value)
            is Project -> encoder.encodeSerializableValue(Project.serializer(), value)
            is User -> encoder.encodeSerializableValue(User.serializer(), value)
            is List<*> -> {
                if (value.isEmpty()) {
                    encoder.encodeSerializableValue(ListSerializer(String.serializer()), emptyList<String>())
                } else if (value[0] is ProjectCreation) {
                    encoder.encodeSerializableValue(
                        ListSerializer(ProjectCreation.serializer()),
                        value as List<ProjectCreation>
                    )
                } else if (value[0] is Project) {
                    encoder.encodeSerializableValue(ListSerializer(Project.serializer()), value as List<Project>)
                }
            }

            is Map<*, *> -> {
                // Check if the map's keys and values are of a type that can be serialized
                if (value.keys.all { it is String }) {
                    val mapSerializer = MapSerializer(String.serializer(), DataSerializer)
                    encoder.encodeSerializableValue(mapSerializer, value as Map<String, Any>)
                } else {
                    encoder.encodeString(value.toString())
                }
            }

            else -> encoder.encodeNull()
        }
    }

    override fun deserialize(decoder: Decoder): Any? {
        throw NotImplementedError("Deserialization is not supported")
    }
}