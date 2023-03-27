package su.funk.diesel.dsl


import su.funk.diesel.runtime.ScriptContext

class Specs {
    static def service(@DelegatesTo(value = ServiceSpec, strategy = Closure.DELEGATE_FIRST) Closure cl) {
        return BaseSpec.execClosure(cl, new ServiceSpec()) {
            ScriptContext.get().addServiceSpec(it)
        }
    }
}
