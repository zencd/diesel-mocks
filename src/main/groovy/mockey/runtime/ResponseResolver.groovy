package mockey.runtime

import groovy.util.logging.Slf4j
import mockey.model.RuleModel
import mockey.model.ServiceModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Slf4j
class ResponseResolver {

    static ResponseEntity resolve(RequestInfo requestInfo, List<ServiceModel> services) {
        for (ServiceModel aService : services) {
            for (RuleModel aRule : aService.rules) {
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
