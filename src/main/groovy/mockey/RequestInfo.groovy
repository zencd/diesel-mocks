package mockey

import groovy.transform.ToString
import jakarta.servlet.http.HttpServletRequest

@ToString
class RequestInfo {
    String method
    String path
    byte[] body

    static RequestInfo collect(HttpServletRequest httpRequest, InputStream inputStream) {
        def body = inputStream ? inputStream.readAllBytes() : null
        return new RequestInfo(
                method: httpRequest.method,
                path: httpRequest.requestURI,
                body: body,
        )
    }
}
