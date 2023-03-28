package su.funk.diesel.web

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.parser.OpenAPIV3Parser
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlinSampler.SwaggerSampler

@SpringBootApplication
class SpringApp {

	static void main(String[] args) {
		//println "new Foo().foo(): ${new Foo().foo()}"
		testKotlin()
		SpringApplication.run(SpringApp, args)
	}

	private static OpenAPI testKotlin() {
		OpenAPI spec = new OpenAPIV3Parser().read("http://swagger.g4lab.com/2.10.0-SNAPSHOT/FAS/FAS.yaml")
		def ss = new SwaggerSampler()
		def schema = spec.components.schemas["TokenWithContext"]
		def res = ss.collectSchemaExample(spec, schema)
		def s = 0
	}
}
