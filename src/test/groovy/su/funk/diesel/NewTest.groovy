package su.funk.diesel

import org.junit.Ignore
import org.junit.Test

class NewTest extends TestBase {
    //@Ignore
    @Test
    void statusCode500() {
        rules {
            rule {
                line "GET", "/statusCode500"
                responseBeginsHere 500
                text 'statusCode500'
            }
        }
        def req = req('GET', '/test1/statusCode500')
        def resp = resp(
                500,
                ['content-type': 'text/plain'],
                'statusCode500')
        resolveAndVerify(req, resp)
    }
}
