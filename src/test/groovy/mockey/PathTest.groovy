package mockey

import org.junit.Test

class PathTest extends TestBase {

    @Test
    void 'exact match'() {
        def req = req('GET', '/test2/users/joe')
        def resp = resp(
                200,
                ANY_HEADERS,
                'exact match')
        resolveAndVerify(req, resp)
    }

    @Test
    void 'in-path variable'() {
        def req = req('GET', '/test2/users/chandler')
        def resp = resp(
                200,
                ANY_HEADERS,
                'in-path variable')
        resolveAndVerify(req, resp)
    }

    @Test
    void 'wildcard in path 1'() {
        def req = req('GET', '/test2/wildcard/111')
        def resp = resp(
                200,
                ANY_HEADERS,
                'wildcard in path')
        resolveAndVerify(req, resp)
    }

    @Test
    void 'wildcard in path 2'() {
        def req = req('GET', '/test2/wildcard/111/222')
        def resp = resp(
                200,
                ANY_HEADERS,
                'wildcard in path')
        resolveAndVerify(req, resp)
    }
}
