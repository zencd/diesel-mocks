package kotlinSampler

import io.swagger.v3.oas.models.Operation
import io.swagger.v3.parser.OpenAPIV3Parser

fun main() {
    println("Hello world!")
    val spec = OpenAPIV3Parser().read("file:///Users/pasza/projects/diesel-mocks/src/test/resources/petstore3.yaml")
    val ss = SwaggerSampler()
    val paths = spec.paths
    val get: Operation = paths["/pet/findByStatus"]!!.get
    val schemas = spec.components.schemas.values
    //val schema = spec.components.schemas["TokenWithContext"]!!
    //val schema = schemas.first()
    //val ok = schema is Schema
    //val res = ss.collectSchemaExample(spec, schema)
    val s = 0
}
