package mockey

import mockey.runtime.ScriptExecutor

def exec = new ScriptExecutor()
exec.processDir('../../../../rules')
println "services: ${exec.context.services.size()}"
def s = 0
