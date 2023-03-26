package mockey

import org.junit.Test

class FeatureTest extends TestBase {

    @Test
    void 'json resp with string'() {
        def req = req('GET', '/test1/jsonRespWithString')
        def resp = resp(
                200,
                ['content-type': 'application/json'],
                '"jsonRespWithString"')
        resolveAndVerify(req, resp)
    }

    @Test
    void 'paramCheck positive'() {
        def req = req('GET', '/test1/paramCheck', [a: 'b'])
        def resp = resp(
                200,
                ['content-type': 'text/plain'],
                'paramCheck')
        resolveAndVerify(req, resp)
    }

    @Test
    void 'paramCheck negative'() {
        def req = req('GET', '/test1/paramCheck', [a: 'B'])
        resolveAndVerify404(req)
    }

    @Test
    void 'paramCheckWithPredicate positive'() {
        def req = req('GET', '/test1/paramCheckWithPredicate', [a: 'b'])
        def resp = resp(
                200,
                ['content-type': 'text/plain'],
                'paramCheckWithPredicate')
        resolveAndVerify(req, resp)
    }

    @Test
    void 'paramCheckWithPredicate negative'() {
        def req = req('GET', '/test1/paramCheckWithPredicate', [a: 'B'])
        resolveAndVerify404(req)
    }

    @Test
    void 'responseHeader'() {
        def req = req('GET', '/test1/responseHeader')
        def resp = resp(
                200,
                ['x-outgoing': 'hello', 'content-type': 'text/plain'],
                'responseHeader')
        resolveAndVerify(req, resp)
    }
}