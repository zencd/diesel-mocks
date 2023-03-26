package mockey.model

import mockey.HeaderPredicate
import mockey.RequestInfo

import java.util.function.Predicate

class ReqModel {
    RuleModel ruleModel
    String method = 'GET'
    String path
    Predicate<String> pathPredicate
    List<HeaderPredicate> headers = []

    boolean matches(RequestInfo requestInfo) {
        if (method != requestInfo.method) {
            return false
        }
        if (!pathMatches(requestInfo)) {
            return false
        }
        if (!allHeadersMatches(requestInfo)) {
            return false
        }
        return true
    }

    private boolean pathMatches(RequestInfo requestInfo) {
        String fullPath = ruleModel.serviceModel.path + this.path
        if (requestInfo.path.startsWith(ruleModel.serviceModel.path)) {
            String subPath = requestInfo.path.substring(ruleModel.serviceModel.path.length())
            if (pathPredicate) {
                return pathPredicate.test(subPath)
            }
        }
        return fullPath == requestInfo.path
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
