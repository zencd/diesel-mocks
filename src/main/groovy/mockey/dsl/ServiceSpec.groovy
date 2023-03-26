package mockey.dsl

import mockey.model.ServiceModel

class ServiceSpec extends BaseSpec {

    private ServiceModel model = new ServiceModel()

    def rule(@DelegatesTo(RuleSpec) Closure cl) {
        return execClosure(cl, new RuleSpec(this)) {
            model.rules.add(it.model)
        }
    }
    void path(String path) {
        model.path = path
    }
    void name(String name) {
        model.name = name
    }
    void swagger(String url) {
        model.swaggerUrl = url
    }
}