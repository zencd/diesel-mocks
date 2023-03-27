package su.funk.diesel

import su.funk.diesel.runtime.ScriptExecutor

def exec = new ScriptExecutor()
exec.processDir('../../../../rules')
println "services: ${exec.context.services.size()}"
def s = 0
