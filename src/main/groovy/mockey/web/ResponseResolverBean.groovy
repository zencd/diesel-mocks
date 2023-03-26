package mockey.web

import groovy.util.logging.Slf4j
import mockey.runtime.RequestInfo
import mockey.runtime.ResponseResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Slf4j
@Component
class ResponseResolverBean {

    @Autowired
    private RuleLoader ruleLoader

    ResponseEntity resolve(RequestInfo requestInfo) {
        return ResponseResolver.resolve(requestInfo, ruleLoader.scriptContext.services)
    }
}
