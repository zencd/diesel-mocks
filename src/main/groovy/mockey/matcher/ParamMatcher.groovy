package mockey.matcher

import groovy.transform.TupleConstructor
import mockey.model.ReqModel
import mockey.predicate.ParamPredicate
import mockey.runtime.RequestInfo

@TupleConstructor(includeFields = true)
class ParamMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

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
