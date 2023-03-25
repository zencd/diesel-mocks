package mockey.dsl

import mockey.ScriptContext

class Specs {
    static def service(@DelegatesTo(ServiceSpec) Closure cl) {
        def spec = new ServiceSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()

        //ScriptContext.data['service'] = 'a value from spec ' + System.nanoTime()
        ScriptContext.get().addServiceSpec(spec)

        return spec
    }
}





