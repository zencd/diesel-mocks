package su.funk.diesel

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.parser.OpenAPIV3Parser
import org.junit.Test

class InvestTest {
    @Test
    void test_() {
        //OpenAPI openAPI = new OpenAPIV3Parser().read("https://petstore3.swagger.io/api/v3/openapi.json");
        OpenAPI openAPI = new OpenAPIV3Parser().read("http://swagger.g4lab.com/15.0.0-SNAPSHOT/CHAIMA/CHAIMA.yaml");
        def schemas = openAPI.components.schemas.values()
        def s = 0
    }
}
