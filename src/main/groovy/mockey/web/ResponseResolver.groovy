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
                if (matches(aRule, requestInfo)) {
                    def resp = getResponseInfo(aRule)
                    return resp
                }
            }
        }
        log.warn("No mock found for ${requestInfo}")
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    boolean matches(RuleSpec rule, RequestInfo request) {
        def req1 = rule.request
        def method1 = rule.request.method
        def method2 = request.method
        if (method1 != method2) {
            return false
        }
        if (rule.request.path != request.path) {
            return false
        }
        return true
    }

    private ResponseEntity<byte[]> getResponseInfo(RuleSpec rule) {
        byte[] binary = getRespBody(rule)
        return new ResponseEntity(binary, rule.response.headers, rule.response.statusCode);
    }

    private byte[] getRespBody(RuleSpec rule) {
        def body = rule.response.body
        if (body == null) {
            return ArrayUtils.EMPTY_BYTE_ARRAY
        } else if (body instanceof String) {
            return body.getBytes(StandardCharsets.UTF_8)
        } else if (body instanceof byte[]) {
            return body
        } else {
            return body.toString().getBytes(StandardCharsets.UTF_8)
        }
    }
}
