package su.funk.diesel.model

import static su.funk.diesel.inject.Injection.getBean
import su.funk.diesel.runtime.ScriptConfig
import su.funk.diesel.util.Utils

import java.util.function.Predicate

class ServiceModel {
    String path
    Predicate<String> pathPredicate
    String name
    String swaggerUrl
    List<RuleModel> rules = []

    private final scriptConfig = getBean(ScriptConfig)

    void setSwaggerUrl(String swaggerUrl) {
        // do resolve things ASAP to make a stacktrace helpful
        if (Utils.isUrl(swaggerUrl)) {
            this.swaggerUrl = swaggerUrl
        } else {
            def file = new File(scriptConfig.resourcesDir, swaggerUrl)
            assert file.exists() : "Missing file ${file}"
            this.swaggerUrl = file.absolutePath
        }
    }
}
