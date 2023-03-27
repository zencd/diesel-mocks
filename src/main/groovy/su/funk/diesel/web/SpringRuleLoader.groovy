package su.funk.diesel.web

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import su.funk.diesel.runtime.ScriptContext
import su.funk.diesel.runtime.ScriptExecutor

@Component
class SpringRuleLoader {

    ScriptContext scriptContext = new ScriptContext()
    //Map<String, ServiceModel>

    @EventListener(value = ApplicationReadyEvent.class)
    void loadRules() {
        def exec = new ScriptExecutor()
        exec.processDir('./rules')
        this.scriptContext = exec.context
    }
}
