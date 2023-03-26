package mockey.matcher

import mockey.model.ReqModel
import mockey.predicate.HeaderPredicate
import mockey.predicate.ParamPredicate
import mockey.runtime.RequestInfo

class HeaderMatcher {

    private final ReqModel reqModel
    private final RequestInfo requestInfo

    HeaderMatcher(ReqModel reqModel, RequestInfo requestInfo) {
        this.reqModel = reqModel
        this.requestInfo = requestInfo
    }

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
