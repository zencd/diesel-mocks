package mockey.dsl

class ServiceSpec {
    private String path
    private String name
    private String swaggerUrl
    private List<RuleSpec> rules = []

    def rule(@DelegatesTo(RuleSpec) Closure cl) {
        def spec = new RuleSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        rules.add(spec)
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
        this.path = path
    }
    void name(String name) {
        this.name = name
    }
    void swagger(String url) {
        this.swaggerUrl = url
    }
}