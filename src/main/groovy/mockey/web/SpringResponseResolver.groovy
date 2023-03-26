package mockey.web

import groovy.util.logging.Slf4j
import mockey.runtime.RequestInfo
import mockey.runtime.ResponseInfo
import mockey.runtime.ResponseResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Slf4j
@Component
class SpringResponseResolver {

    @Autowired
    private SpringRuleLoader ruleLoader

    ResponseInfo resolve(RequestInfo requestInfo) {
        return ResponseResolver.resolve(requestInfo, ruleLoader.scriptContext.services)
    }
}
