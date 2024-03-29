package su.funk.diesel.dsl


import su.funk.diesel.model.ServiceModel

class ServiceSpec extends BaseSpec {

    private ServiceModel model = new ServiceModel()

    def rule(@DelegatesTo(value = RuleSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {
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