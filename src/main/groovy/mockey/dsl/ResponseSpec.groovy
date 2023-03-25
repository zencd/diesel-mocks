package mockey.dsl

import org.springframework.http.HttpHeaders
import org.springframework.util.MultiValueMap

class ResponseSpec {
    private int statusCode = 200
    private Object body
    private HttpHeaders headers = new HttpHeaders()

    def json(String body) {
        this.headers.add('content-type', 'application/json')
        this.body = body
    }
    def json(Map body) {
        this.headers.add('content-type', 'application/json')
        this.body = body
    }
    def json(List body) {
        this.headers.add('content-type', 'application/json')
        this.body = body
    }
    void header(String name, String value) {
        this.headers.add(name, value)
    }
    def status(int code) {
        this.statusCode = code
    }
}