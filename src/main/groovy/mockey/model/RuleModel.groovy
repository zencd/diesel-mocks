package mockey.model

import mockey.runtime.RequestInfo
import mockey.runtime.ResponseInfo
import org.springframework.http.ResponseEntity

class RuleModel {
    ServiceModel serviceModel
    ReqModel request = new ReqModel(ruleModel: this)
    RespModel response = new RespModel()
    String jsonMatch

    ResponseInfo getResponseIfMatched(RequestInfo requestInfo) {
        if (request.matches(requestInfo)) {
            return response.createResponse2()
        }
        return null
    }
}
