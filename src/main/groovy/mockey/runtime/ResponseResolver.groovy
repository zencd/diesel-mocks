package mockey.runtime

import groovy.util.logging.Slf4j
import mockey.model.RuleModel
import mockey.model.ServiceModel

@Slf4j
class ResponseResolver {

    private final ScriptContext context

    ResponseResolver(ScriptContext context) {
        this.context = context
    }

    ResponseInfo resolve(RequestInfo requestInfo) {
        for (ServiceModel aService : context.services) {
            for (RuleModel aRule : aService.rules) {
                def response = aRule.getResponseIfMatched(requestInfo)
                if (response != null) {
                    return response
                }
            }
        }
        log.warn("No mock found for ${requestInfo}")
        return ResponseInfo.NOT_FOUND
    }
}
