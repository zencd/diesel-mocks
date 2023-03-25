package mockey.model

import mockey.RequestInfo
import org.springframework.http.ResponseEntity

class RuleModel {
    ReqModel request = new ReqModel()
    RespModel response = new RespModel()

    ResponseEntity getResponseIfMatched(RequestInfo requestInfo) {
        if (request.matches(requestInfo)) {
            return response.createResponse()
        }
        return null
    }
}
