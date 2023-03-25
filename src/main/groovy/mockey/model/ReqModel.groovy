package mockey.model

import mockey.HeaderPredicate
import mockey.RequestInfo

import java.util.function.Predicate

class ReqModel {
    String method = 'GET'
    String path
    List<HeaderPredicate> headers = []

    boolean matches(RequestInfo requestInfo) {
        if (method != requestInfo.method) {
            return false
        }
        if (path != requestInfo.path) {
            return false
        }
        if (!allHeadersMatches(requestInfo)) {
            return false
        }
        return true
    }

    private boolean allHeadersMatches(RequestInfo requestInfo) {
        for (def hdr : headers) {
            if (!oneHeaderMatches(hdr, requestInfo)) {
                return false
            }
        }
        return true
    }

    private boolean oneHeaderMatches(HeaderPredicate predicate, RequestInfo requestInfo) {
        for (String key : requestInfo.headers.keySet()) {
            if (key.equalsIgnoreCase(predicate.key)) {
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
