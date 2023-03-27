package su.funk.diesel.matcher

import groovy.transform.TupleConstructor
import su.funk.diesel.model.ReqModel
import su.funk.diesel.runtime.RequestInfo
import su.funk.diesel.util.Utils

@TupleConstructor(includeFields = true)
class PathMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

    boolean pathMatches() {
        def ruleModel = reqModel.ruleModel
        if (requestInfo.path.startsWith(ruleModel.serviceModel.path)) {
            String reqPathWithoutServicePrefix = requestInfo.path.substring(ruleModel.serviceModel.path.length())
            String regex = Utils.pathPatternToRegex(reqModel.path)
            if (regex == reqModel.path) {
                if (reqPathWithoutServicePrefix == reqModel.path) {
                    return true
                }
            } else {
                if (reqPathWithoutServicePrefix.matches(regex)) {
                    return true
                }
            }
        }
        return false
    }
}
