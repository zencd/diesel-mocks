package mockey

import mockey.runtime.RequestInfo
import mockey.runtime.ResponseResolver
import mockey.runtime.ScriptExecutor
import org.junit.Test
import org.springframework.http.HttpHeaders

class MyTest {

    def context = loadRules()
    def resolver = new ResponseResolver(context)

    static loadRules() {
        return ScriptExecutor.processAll('src/test/groovy/mockey/rules4test')
    }

    @Test
    void test_() {
        def req = req('GET', '/test/users/users/joe')
        def resp = resolver.resolve(req)
        assert resp.headers['x-outgoing'] == ['hello']
        assert resp.headers['content-type'] == ['application/json']
        assert resp.statusCode == 200
        assert new String(resp.body, 'utf-8') == '{"name":"Привет Joe"}'
    }

    static RequestInfo req(String method, String path, HttpHeaders headers = null, byte[] body = null) {
        return new RequestInfo(method: method, path: path, headers: headers, body: body)
    }
}
