package mockey.model

import mockey.matcher.HeaderMatcher
import mockey.matcher.ParamMatcher
import mockey.matcher.PathMatcher
import mockey.runtime.RequestInfo
import mockey.predicate.HeaderPredicate
import mockey.predicate.ParamPredicate

import java.util.function.Predicate

class ReqModel {
    RuleModel ruleModel
    String method = 'GET'
    String path
    List<ParamPredicate> paramPredicates = []
    List<HeaderPredicate> headers = []

    boolean matches(RequestInfo requestInfo) {
        if (method != requestInfo.method) {
            return false
        }
        if (!(new PathMatcher(this, requestInfo).pathMatches())) {
            return false
        }
        if (!(new ParamMatcher(this, requestInfo).paramsMatches())) {
            return false
        }
        if (!(new HeaderMatcher(this, requestInfo).headersMatches())) {
            return false
        }
        return true
    }
}
