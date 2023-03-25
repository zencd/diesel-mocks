package mockey.model

import org.apache.commons.lang3.ArrayUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity

import java.nio.charset.StandardCharsets

class RespModel {
    int statusCode = 200
    Object body
    HttpHeaders headers = new HttpHeaders()

    ResponseEntity<byte[]> createResponse() {
        byte[] binary = getRespBody()
        return new ResponseEntity(binary, headers, statusCode);
    }

    private byte[] getRespBody() {
        if (body == null) {
            return ArrayUtils.EMPTY_BYTE_ARRAY
        } else if (body instanceof String) {
            return body.getBytes(StandardCharsets.UTF_8)
        } else if (body instanceof Closure) {
            assert false : "not impl"
        } else if (body instanceof byte[]) {
            return body
        } else {
            return body.toString().getBytes(StandardCharsets.UTF_8)
        }
    }
}
