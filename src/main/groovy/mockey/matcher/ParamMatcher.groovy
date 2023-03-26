package mockey.matcher

import mockey.model.ReqModel
import mockey.predicate.ParamPredicate
import mockey.runtime.RequestInfo

class ParamMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

    ParamMatcher(ReqModel reqModel, RequestInfo requestInfo) {
        this.reqModel = reqModel
        this.requestInfo = requestInfo
    }

    boolean paramsMatches() {
        for (ParamPredicate pp : reqModel.paramPredicates) {
            if (!paramMatches(pp)) {
                return false
            }
        }
        return true
    }

    boolean paramMatches(ParamPredicate predicate) {
        for (String key : requestInfo.params.keySet()) {
            if (key == predicate.name) {
                String value = requestInfo.params[key]
                if (predicate.predicate.test(value)) {
                    return true
                }
            }
        }
        return false
    }
}
