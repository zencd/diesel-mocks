package kotlinSampler

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.media.ArraySchema
import io.swagger.v3.oas.models.media.ComposedSchema
import io.swagger.v3.oas.models.media.DateSchema
import io.swagger.v3.oas.models.media.DateTimeSchema
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.Schema
import java.time.OffsetDateTime
import java.util.*

class SwaggerSampler {

    fun collectSchemaExample(spec: OpenAPI, schema: Schema<*>): Any? {

        // collector's entry point

        // $ref takes precedence, per spec:
        //   "Any sibling elements of a $ref are ignored. This is because
        //   $ref works by replacing itself and everything on its level
        //   with the definition it is pointing at."
        // See: https://swagger.io/docs/specification/using-ref/
        val example: Any? = if (Objects.nonNull(schema.`$ref`)) {
            val referent = RefUtil.lookupSchemaRef(spec, schema)
            collectSchemaExample(spec, referent)
        } else if (Objects.nonNull(schema.example)) {
            if (schema is DateTimeSchema) {
                DateTimeUtil.DATE_TIME_FORMATTER.format(schema.getExample() as OffsetDateTime)
            } else if (schema is DateSchema) {
                DateTimeUtil.DATE_FORMATTER.format((schema.getExample() as Date).toInstant())
            } else {
                schema.example
            }
        } else if (Objects.nonNull(schema.properties)) {
            buildFromProperties(spec, schema.properties)
        } else if (ObjectSchema::class.java.isAssignableFrom(schema.javaClass)) {
            val objectSchema = schema as ObjectSchema?
            buildFromProperties(spec, objectSchema!!.properties)
        } else if (ArraySchema::class.java.isAssignableFrom(schema.javaClass)) {
            buildFromArraySchema(spec, schema as ArraySchema?)
        } else if (ComposedSchema::class.java.isAssignableFrom(schema.javaClass)) {
            buildFromComposedSchema(spec, schema as ComposedSchema?)
        } else {
            getPropertyDefault(schema)
        }
        return example
    }

    private fun buildFromArraySchema(spec: OpenAPI, schema: ArraySchema?): List<Any?> {
        // items may be a schema type with multiple children
        val items = schema!!.items
        val examples: MutableList<Any?> = mutableListOf()
        examples.add(collectSchemaExample(spec, items))
        return examples
    }

    private fun buildFromComposedSchema(spec: OpenAPI, schema: ComposedSchema?): Any? {
        val example: Any? = if (Objects.nonNull(schema!!.allOf) && schema.allOf.isNotEmpty()) {
            val allOf = schema.allOf

            // Combine properties of 'allOf'
            // See: https://swagger.io/docs/specification/data-models/oneof-anyof-allof-not/
            val combinedExampleProperties: MutableMap<String, Any> = mutableMapOf()
            allOf.forEach { s ->
                val exampleMap = collectSchemaExample(spec, s)
                if (Objects.nonNull(exampleMap) && exampleMap is Map<*, *>) {
                    // FIXME code defensively around this cast
                    @Suppress("UNCHECKED_CAST")
                    combinedExampleProperties.putAll((exampleMap as Map<String, Any>?)!!)
                }
            }
            combinedExampleProperties

        } else if (Objects.nonNull(schema.oneOf) && schema.oneOf.isNotEmpty()) {
            val oneOf = schema.oneOf
            collectSchemaExample(spec, oneOf[0])

        } else if (Objects.nonNull(schema.anyOf) && schema.anyOf.isNotEmpty()) {
            val anyOf = schema.anyOf
            collectSchemaExample(spec, anyOf[0])

        } else if (Objects.nonNull(schema.not)) {
            null

        } else {
            throw IllegalStateException("Invalid composed schema; missing or empty [allOf, oneOf, anyOf]")
        }
        return example
    }

    private fun buildFromProperties(spec: OpenAPI, properties: Map<String, Schema<*>>): Map<String, Any?> {
        return if (Objects.isNull(properties)) {
            emptyMap<String, Any>()
        } else {
            properties.entries.associate { (k, v) -> k to collectSchemaExample(spec, v) }
        }
    }

    private fun getPropertyDefault(schema: Schema<*>?): Any? {
        // if a non-empty enum exists, choose the first value
        if (Objects.nonNull(schema!!.enum) && !schema.enum.isEmpty()) {
            return schema.enum[0]
        }

        // fall back to a default for the type
        if (Objects.nonNull(schema.type)) {
            val defaultValueProvider = DEFAULT_VALUE_PROVIDERS[schema.type]!!
            return if (Objects.nonNull(defaultValueProvider)) {
                defaultValueProvider.provide(schema)
            } else {
                null
            }
        }
        return null
    }
}