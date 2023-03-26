package mockey.dsl

import java.util.function.Consumer

class BaseSpec {
    static  <T> T execClosure(Closure cl, T spec, Consumer<T> listener) {
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        listener.accept(spec)
        return spec
    }
}
