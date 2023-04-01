package su.funk.diesel.runtime

import groovy.util.logging.Slf4j
import su.funk.diesel.model.RuleModel
import su.funk.diesel.model.ServiceModel

@Slf4j
class ResponseResolver {

    private final ScriptContext context

    ResponseResolver(ScriptContext context) {
        this.context = context
    }

    ResponseInfo resolve(RequestInfo requestInfo) {
        for (ServiceModel aService : context.services) {
            if (!requestInfo.path.startsWith(aService.path)) {
                continue
            }
            for (RuleModel aRule : aService.rules) {
                def response = aRule.getResponseIfMatched(requestInfo)
                if (response != null) {
                    return response
                }
            }
            // todo use openapi if no rule found
        }
        log.warn("No mock found for ${requestInfo}")
        return ResponseInfo.NOT_FOUND
    }
}
