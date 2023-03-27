package su.funk.diesel.web

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import su.funk.diesel.runtime.RequestInfo
import su.funk.diesel.runtime.ResponseInfo
import su.funk.diesel.runtime.ResponseResolver

@Slf4j
@Component
class SpringResponseResolver {

    @Autowired
    private SpringRuleLoader ruleLoader

    ResponseInfo resolve(RequestInfo requestInfo) {
        def resolver = new ResponseResolver(ruleLoader.scriptContext)
        return resolver.resolve(requestInfo)
    }
}
