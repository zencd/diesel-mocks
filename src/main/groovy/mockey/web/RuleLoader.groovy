package mockey.web


import mockey.load.ScriptContext
import mockey.load.ScriptExecutor
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class RuleLoader {

    ScriptContext scriptContext = new ScriptContext()
    //Map<String, ServiceModel>

    @EventListener(value = ApplicationReadyEvent.class)
    void loadRules() {
        def exec = new ScriptExecutor()
        exec.processAll('./rules')
        this.scriptContext = exec.context
    }
}
