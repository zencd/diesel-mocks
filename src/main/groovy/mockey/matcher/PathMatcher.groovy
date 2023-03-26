package mockey.matcher

import groovy.transform.TupleConstructor
import mockey.model.ReqModel
import mockey.runtime.RequestInfo
import mockey.util.Utils

@TupleConstructor(includeFields = true)
class PathMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

    boolean pathMatches() {
        def ruleModel = reqModel.ruleModel
        if (requestInfo.path.startsWith(ruleModel.serviceModel.path)) {
            String subPath = requestInfo.path.substring(ruleModel.serviceModel.path.length())
            String regex = Utils.pathPatternToRegex(reqModel.path)
            if (regex != reqModel.path) {
                if (subPath.matches(regex)) {
                    return true
                }
            }
            if (reqModel.pathPredicate) {
                return reqModel.pathPredicate.test(subPath)
            }
        }
        return false
    }
}
