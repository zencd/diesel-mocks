package su.funk.diesel.runtime

class ScriptConfig {
    File projectRoot

    def getResourcesDir() {
        new File(projectRoot, "src/test/resources")
    }
}
