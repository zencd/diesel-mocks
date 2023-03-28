package kotlinSampler

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.responses.ApiResponse

object RefUtil {
    private const val REF_PREFIX_RESPONSES = "#/components/responses/"
    private const val REF_PREFIX_SCHEMAS = "#/components/schemas/"

    fun lookupResponseRef(spec: OpenAPI, referrer: ApiResponse): ApiResponse {
        if (referrer.`$ref`.startsWith(REF_PREFIX_RESPONSES)) {
            val responseName = referrer.`$ref`.substring(REF_PREFIX_RESPONSES.length)
            return spec.components?.responses?.get(responseName)
                ?: throw IllegalStateException("Referenced response not found in components section: $responseName")
        } else {
            throw IllegalStateException("Unsupported response \$ref: ${referrer.`$ref`}")
        }
    }

    fun lookupSchemaRef(spec: OpenAPI, referrer: Schema<*>): Schema<*> {
        if (referrer.`$ref`.startsWith(REF_PREFIX_SCHEMAS)) {
            val schemaName = referrer.`$ref`.substring(REF_PREFIX_SCHEMAS.length)
            return spec.components?.schemas?.get(schemaName)
                ?: throw IllegalStateException("Referenced schema not found in components section: $schemaName")
        } else {
            throw IllegalStateException("Unsupported schema \$ref: ${referrer.`$ref`}")
        }
    }
}