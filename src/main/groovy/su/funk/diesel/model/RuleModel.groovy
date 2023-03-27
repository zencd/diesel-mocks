package su.funk.diesel.model


import su.funk.diesel.runtime.RequestInfo
import su.funk.diesel.runtime.ResponseInfo

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
