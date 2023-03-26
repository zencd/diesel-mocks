package mockey.load

import mockey.dsl.ServiceSpec

class ScriptContext {

    List<ServiceSpec> services = []

    private static ThreadLocal<ScriptContext> threadLocal = ThreadLocal.withInitial {
        new ScriptContext()
    }

    static ScriptContext get() {
        return threadLocal.get()
    }

    static void set(ScriptContext value) {
        threadLocal.set(value)
    }

    void addServiceSpec(ServiceSpec serviceSpec) {
        this.services.add(serviceSpec)
    }
}
