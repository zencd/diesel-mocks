package su.funk.diesel

import org.junit.Test

class OpenApiTest extends TestBase {

    @Test
    void 'xxx'() {
        def req = req('GET', '/petstore/pet/findByStatus')
        def resp = resp(
                200,
                ['content-type': 'application/json'],
                'xxx')
        verify(req, resp)
    }
}
