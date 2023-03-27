package su.funk.diesel.runtime

import groovy.transform.ToString
import jakarta.servlet.http.HttpServletRequest
import su.funk.diesel.util.Utils
import org.springframework.http.HttpHeaders

@ToString
//@Builder
class RequestInfo {
    String method
    String path
    Map<String,String> params
    HttpHeaders headers
    byte[] body

    static RequestInfo collect(HttpServletRequest httpRequest, InputStream inputStream) {
        def body = inputStream ? inputStream.readAllBytes() : null
        httpRequest.parameterNames
        return new RequestInfo(
                method: httpRequest.method,
                path: httpRequest.requestURI,
                params: params(httpRequest),
                headers: Utils.httpHeaders(httpRequest),
                body: body,
        )
    }

    static Map<String,String> params(HttpServletRequest httpRequest) {
        def res = new HashMap()
        for (def name : httpRequest.parameterNames) {
            def values = httpRequest.getParameterValues(name)
            res[name] = values[0] // todo review
        }
        return res
    }
}
