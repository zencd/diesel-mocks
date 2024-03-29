package su.funk.diesel.runtime

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@EqualsAndHashCode
@ToString
//@Builder
class ResponseInfo {

    int statusCode
    HttpHeaders headers
    byte[] body

    ResponseEntity toResponseEntity() {
        return new ResponseEntity(body, headers, statusCode);
    }

    static def NOT_FOUND = new ResponseInfo(statusCode: HttpStatus.NOT_FOUND.value(), headers: null, body: null)
}
