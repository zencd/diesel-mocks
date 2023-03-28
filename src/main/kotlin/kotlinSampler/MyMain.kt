package kotlinSampler

import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.parser.OpenAPIV3Parser

fun main() {
    println("Hello world!")
    //val spec = OpenAPIV3Parser().read("http://swagger.g4lab.com/15.0.0-SNAPSHOT/CHAIMA/CHAIMA.yaml")
    val spec = OpenAPIV3Parser().read("http://swagger.g4lab.com/2.10.0-SNAPSHOT/FAS/FAS.yaml")
    val ss = SwaggerSampler()
    val schemas = spec.components.schemas.values
    val schema = spec.components.schemas["TokenWithContext"]!!
    //val schema = schemas.first()
    val ok = schema is Schema
    val res = ss.collectSchemaExample(spec, schema)
    val s = 0
}
