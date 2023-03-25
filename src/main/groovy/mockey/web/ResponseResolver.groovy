package mockey.web

import groovy.util.logging.Slf4j
import mockey.RequestInfo
import mockey.dsl.RuleSpec
import mockey.dsl.ServiceSpec
import org.apache.commons.lang3.ArrayUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

import java.nio.charset.StandardCharsets

@Slf4j
@Component
class ResponseResolver {

    @Autowired
    private RuleLoader ruleLoader

    ResponseEntity resolve(RequestInfo requestInfo) {
        for (ServiceSpec aService : ruleLoader.scriptContext.services) {
            for (RuleSpec aRule : aService.rules) {
                ResponseEntity response = aRule.rule.getResponseIfMatched(requestInfo)
                if (response != null) {
                    return response
                }
            }
        }
        log.warn("No mock found for ${requestInfo}")
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
