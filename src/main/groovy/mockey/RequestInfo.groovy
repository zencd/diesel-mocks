package mockey

import groovy.transform.ToString
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders

import java.util.function.Function
import java.util.stream.Collectors

@ToString
class RequestInfo {
    String method
    String path
    HttpHeaders headers
    byte[] body

    static RequestInfo collect(HttpServletRequest httpRequest, InputStream inputStream) {
        def body = inputStream ? inputStream.readAllBytes() : null
        return new RequestInfo(
                method: httpRequest.method,
                path: httpRequest.requestURI,
                headers: Utils.httpHeaders(httpRequest),
                body: body,
        )
    }

}
