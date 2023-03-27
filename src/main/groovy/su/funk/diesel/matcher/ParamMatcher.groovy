package su.funk.diesel.matcher

import groovy.transform.TupleConstructor
import su.funk.diesel.predicate.ParamPredicate
import su.funk.diesel.model.ReqModel
import su.funk.diesel.runtime.RequestInfo

//@TypeChecked
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
