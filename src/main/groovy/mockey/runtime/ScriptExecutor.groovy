package mockey.runtime

import groovy.util.logging.Slf4j
import mockey.util.Utils

@Slf4j
class ScriptExecutor {
    ScriptContext context = new ScriptContext()

    static ScriptContext processAll(String ruleDirStr) {
        def exec = new ScriptExecutor()
        exec.processDir(ruleDirStr)
        return exec.context
    }

    void processDir(String ruleDirStr) {
        context = new ScriptContext()
        ScriptContext.set(context)
        def ruleDir = new File(ruleDirStr).absoluteFile
        assert ruleDir.exists()
        assert ruleDir.isDirectory()
        for (def aFile : ruleDir.listFiles()) {
            aFile = Utils.normalize(aFile)
            processFile(aFile)
        }
        log.info("Loaded ${context.services.size()} services")
        ScriptContext.set(null)
    }

    void processFile(File scriptFile) {
        log.info "executing ${scriptFile}"
        Class groovyClass = new GroovyClassLoader(getClass().getClassLoader()).parseClass(scriptFile);
        GroovyObject scriptObject = (GroovyObject) groovyClass.newInstance()
        scriptObject.run()
    }
}
