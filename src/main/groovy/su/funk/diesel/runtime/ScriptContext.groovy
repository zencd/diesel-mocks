package su.funk.diesel.runtime

import su.funk.diesel.dsl.ServiceSpec
import su.funk.diesel.model.ServiceModel

class ScriptContext {

    List<ServiceModel> services = []

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
        this.services.add(serviceSpec.model)
    }
}
