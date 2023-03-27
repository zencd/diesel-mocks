package su.funk.diesel.model

import su.funk.diesel.matcher.HeaderMatcher
import su.funk.diesel.matcher.ParamMatcher
import su.funk.diesel.matcher.PathMatcher
import su.funk.diesel.predicate.ParamPredicate
import su.funk.diesel.predicate.HeaderPredicate
import su.funk.diesel.runtime.RequestInfo

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
