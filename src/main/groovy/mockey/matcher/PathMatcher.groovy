package mockey.matcher

import groovy.transform.TupleConstructor
import mockey.model.ReqModel
import mockey.runtime.RequestInfo

@TupleConstructor(includeFields = true)
class PathMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

    boolean pathMatches() {
        def ruleModel = reqModel.ruleModel
        String fullPath = ruleModel.serviceModel.path + reqModel.path
        if (requestInfo.path.startsWith(ruleModel.serviceModel.path)) {
            String subPath = requestInfo.path.substring(ruleModel.serviceModel.path.length())
            if (reqModel.pathPredicate) {
                return reqModel.pathPredicate.test(subPath)
            }
        }
        return fullPath == requestInfo.path
    }

}
