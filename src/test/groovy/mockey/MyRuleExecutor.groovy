package mockey

def exec = new ScriptExecutor()
exec.processAll('../../../../rules')
println "services: ${exec.context.services.size()}"
def s = 0
