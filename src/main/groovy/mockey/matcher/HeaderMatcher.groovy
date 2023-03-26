package mockey.matcher

import groovy.transform.TupleConstructor
import mockey.model.ReqModel
import mockey.predicate.HeaderPredicate
import mockey.runtime.RequestInfo

@TupleConstructor(includeFields = true)
class HeaderMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

    boolean headersMatches() {
        for (def hdr : reqModel.headers) {
            if (!oneHeaderMatches(hdr)) {
                return false
            }
        }
        return true
    }

    private boolean oneHeaderMatches(HeaderPredicate predicate) {
        for (String key : requestInfo.headers.keySet()) {
            if (key.equalsIgnoreCase(predicate.name)) {
                for (String value : requestInfo.headers.get(key)) {
                    if (predicate.predicate.test(value)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}
