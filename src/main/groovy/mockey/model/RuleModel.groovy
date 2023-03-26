package mockey.model

import mockey.RequestInfo
import org.springframework.http.ResponseEntity

class RuleModel {
    ServiceModel serviceModel
    ReqModel request = new ReqModel(ruleModel: this)
    RespModel response = new RespModel()

    ResponseEntity getResponseIfMatched(RequestInfo requestInfo) {
        if (request.matches(requestInfo)) {
            return response.createResponse()
        }
        return null
    }
}
