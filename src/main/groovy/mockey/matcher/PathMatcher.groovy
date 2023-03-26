package mockey.matcher

import mockey.model.ReqModel
import mockey.model.RuleModel
import mockey.predicate.HeaderPredicate
import mockey.runtime.RequestInfo

class PathMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

    PathMatcher(ReqModel reqModel, RequestInfo requestInfo) {
        this.reqModel = reqModel
        this.requestInfo = requestInfo
    }

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
