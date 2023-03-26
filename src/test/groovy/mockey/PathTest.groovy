package mockey

import org.junit.Test

class PathTest extends TestBase {

    @Test
    void 'get concrete user'() {
        def req = req('GET', '/test2/users/joe')
        def resp = resp(
                200,
                ANY_HEADERS,
                'get concrete user')
        resolveAndVerify(req, resp)
    }

    //@Test
    //void 'get a wildcard user'() {
    //    def req = req('GET', '/test2/users/wildcard')
    //    def resp = resp(
    //            200,
    //            ['content-type': 'text/plain'],
    //            'get a wildcard user')
    //    resolveAndVerify(req, resp)
    //}
}
