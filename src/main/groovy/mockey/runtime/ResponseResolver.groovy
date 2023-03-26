package mockey.runtime

import groovy.util.logging.Slf4j
import mockey.model.RuleModel
import mockey.model.ServiceModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Slf4j
class ResponseResolver {

    private final ScriptContext context

    ResponseResolver(ScriptContext context) {
        this.context = context
    }

    ResponseInfo resolve(RequestInfo requestInfo) {
        resolve(requestInfo, context.services)
    }

    static ResponseInfo resolve(RequestInfo requestInfo, List<ServiceModel> services) {
        for (ServiceModel aService : services) {
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
