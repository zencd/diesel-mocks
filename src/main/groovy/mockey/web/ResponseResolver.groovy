package mockey.web

import groovy.util.logging.Slf4j
import mockey.util.RequestInfo
import mockey.dsl.ServiceSpec
import mockey.model.RuleModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Slf4j
@Component
class ResponseResolver {

    @Autowired
    private RuleLoader ruleLoader

    ResponseEntity resolve(RequestInfo requestInfo) {
        for (ServiceSpec aService : ruleLoader.scriptContext.services) {
            for (RuleModel aRule : aService.model.rules) {
                ResponseEntity response = aRule.getResponseIfMatched(requestInfo)
                if (response != null) {
                    return response
                }
            }
        }
        log.warn("No mock found for ${requestInfo}")
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
