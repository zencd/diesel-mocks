package mockey.runtime

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseInfo {

    int statusCode
    HttpHeaders headers
    byte[] body

    ResponseEntity toResponseEntity() {
        return new ResponseEntity(body, headers, statusCode);
    }

    static def NOT_FOUND = new ResponseInfo(statusCode: HttpStatus.NOT_FOUND.value(), headers: null, body: null)
}
