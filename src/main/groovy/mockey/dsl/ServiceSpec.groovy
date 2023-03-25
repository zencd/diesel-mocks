package mockey.dsl

import mockey.model.ServiceModel

class ServiceSpec {

    private ServiceModel model = new ServiceModel()

    def rule(@DelegatesTo(RuleSpec) Closure cl) {
        def spec = new RuleSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        model.rules.add(spec.model)
        return spec
    }
    @Deprecated
    def ruleOld(@DelegatesTo(RuleOldSpec) Closure cl) {
        def spec = new RuleOldSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        //rules.add(spec)
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