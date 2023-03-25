package mockey.dsl

import mockey.model.ServiceModel

class ServiceSpec extends BaseSpec {

    private ServiceModel model = new ServiceModel()

    def rule(@DelegatesTo(RuleSpec) Closure cl) {
        return execClosure(cl, new RuleSpec()) {
            model.rules.add(it.model)
        }
    }

    @Deprecated
    def ruleOld(@DelegatesTo(RuleOldSpec) Closure cl) {
        def spec = new RuleOldSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        return spec
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