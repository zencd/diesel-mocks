package su.funk.diesel


import org.junit.Test

class FeatureTest extends TestBase {

    @Test
    void 'json resp with string'() {
        def req = req('GET', '/test1/jsonRespWithString')
        def resp = resp(
                200,
                ['content-type': 'application/json'],
                '"jsonRespWithString"')
        verify(req, resp)
    }

    @Test
    void 'paramCheck positive'() {
        def req = req('GET', '/test1/paramCheck', [a: 'b'])
        def resp = resp(
                200,
                ['content-type': 'text/plain'],
                'paramCheck')
        verify(req, resp)
    }

    @Test
    void 'paramCheck negative'() {
        def req = req('GET', '/test1/paramCheck', [a: 'B'])
        verify404(req)
    }

    @Test
    void 'paramCheckWithPredicate positive'() {
        def req = req('GET', '/test1/paramCheckWithPredicate', [a: 'b'])
        def resp = resp(
                200,
                ['content-type': 'text/plain'],
                'paramCheckWithPredicate')
        verify(req, resp)
    }

    @Test
    void 'paramCheckWithPredicate negative'() {
        def req = req('GET', '/test1/paramCheckWithPredicate', [a: 'B'])
        verify404(req)
    }

    @Test
    void 'responseHeader'() {
        def req = req('GET', '/test1/responseHeader')
        def resp = resp(
                200,
                ['x-outgoing': 'hello', 'content-type': 'text/plain'],
                'responseHeader')
        verify(req, resp)
    }

    @Test
    void 'statusCode500'() {
        def req = req('GET', '/test1/statusCode500')
        def resp = resp(
                500,
                ANY_HEADERS,
                'statusCode500')
        verify(req, resp)
    }

    @Test
    void 'containsText, positive'() {
        def req = req(
                'POST',
                '/test1/containsText',
                null,
                null,
                '"1.1.1.1"'.bytes // todo use a simpler construction
        )
        def resp = resp(
                200,
                ANY_HEADERS,
                'containsText')
        verify(req, resp)
        // todo add a test against binary body
    }

    @Test
    void 'containsText, negative'() {
        def req = req(
                'POST',
                '/test1/containsText',
                null,
                null,
                '"0.0.0.0"'.bytes
        )
        verify404(req)
    }
}
