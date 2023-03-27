package su.funk.diesel.matcher

import groovy.transform.TupleConstructor
import su.funk.diesel.model.ReqModel
import su.funk.diesel.predicate.BodyPredicate
import su.funk.diesel.predicate.ParamPredicate
import su.funk.diesel.runtime.RequestInfo

//@TypeChecked
@TupleConstructor(includeFields = true)
class BodyMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

    boolean matches() {
        for (BodyPredicate pp : reqModel.bodyPredicates) {
            if (!bodyMatches(pp)) {
                return false
            }
        }
        return true
    }

    boolean bodyMatches(BodyPredicate predicate) {
        byte[] bytes = requestInfo.body ?: [] as byte[]
        def strBody = new String(bytes, 'utf-8')
        return predicate.predicate.test(strBody)
    }
}
