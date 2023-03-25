package mockey

import groovy.util.logging.Slf4j

@Slf4j
class ScriptExecutor {
    ScriptContext context = new ScriptContext()
    void processAll(String ruleDirStr) {
        context = new ScriptContext()
        ScriptContext.set(context)
        //def ruleDirStr = '../../../../rules'
        //def ruleDirStr = './rules'
        def ruleDir = new File(ruleDirStr).absoluteFile
        //def tmp = Utils.normalize(ruleDir)
        assert ruleDir.exists()
        for (def aFile : ruleDir.listFiles()) {
            aFile = Utils.normalize(aFile)
            processFile(aFile)
        }
    }
    void processFile(File scriptFile) {
        log.info "executing ${scriptFile}"
        Class groovyClass = new GroovyClassLoader(getClass().getClassLoader()).parseClass(scriptFile);
        GroovyObject scriptObject = (GroovyObject) groovyClass.newInstance()
        scriptObject.run()
    }
}
