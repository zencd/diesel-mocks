package mockey.model

import mockey.runtime.RequestInfo
import mockey.util.HeaderPredicate
import mockey.util.ParamPredicate

import java.util.function.Predicate

class ReqModel {
    RuleModel ruleModel
    String method = 'GET'
    String path
    List<ParamPredicate> paramPredicates = []
    Predicate<String> pathPredicate
    List<HeaderPredicate> headers = []

    boolean matches(RequestInfo requestInfo) {
        if (method != requestInfo.method) {
            return false
        }
        if (!pathMatches(requestInfo)) {
            return false
        }
        if (!paramsMatches(requestInfo)) {
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

    boolean paramsMatches(RequestInfo requestInfo) {
        for (ParamPredicate pp : paramPredicates) {
            if (!paramMatches(pp, requestInfo)) {
                return false
            }
        }
        return true
    }

    boolean paramMatches(ParamPredicate predicate, RequestInfo requestInfo) {
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
