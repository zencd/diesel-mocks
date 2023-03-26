package mockey.dsl

import mockey.runtime.ScriptContext

class Specs {
    static def service(@DelegatesTo(ServiceSpec) Closure cl) {
        def spec = new ServiceSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()

        ScriptContext.get().addServiceSpec(spec)

        return spec
    }
}
