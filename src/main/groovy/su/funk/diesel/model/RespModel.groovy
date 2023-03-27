package su.funk.diesel.model

import su.funk.diesel.runtime.ResponseInfo
import org.apache.commons.lang3.ArrayUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import java.nio.charset.StandardCharsets

class RespModel {
    int statusCode = 200
    HttpHeaders headers = new HttpHeaders()
    Object body

    ResponseEntity<byte[]> createResponse() {
        byte[] binary = getRespBody(body)
        return new ResponseEntity(binary, headers, statusCode);
    }

    ResponseInfo createResponse2() {
        return new ResponseInfo(
                statusCode: statusCode,
                headers: headers,
                body: getRespBody(body))
    }

    private static byte[] getRespBody(Object body) {
        if (body instanceof Closure) {
            body = body.call()
        }
        return getRespBodyNoClosure(body)
    }

    private static byte[] getRespBodyNoClosure(Object body) {
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

    void setContentTypeJson() {
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    }

    void setTextPlain() {
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
    }
}
