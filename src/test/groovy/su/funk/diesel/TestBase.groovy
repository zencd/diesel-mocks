package su.funk.diesel

import org.springframework.http.HttpHeaders
import su.funk.diesel.runtime.RequestInfo
import su.funk.diesel.runtime.ResponseInfo
import su.funk.diesel.runtime.ResponseResolver
import su.funk.diesel.runtime.ScriptExecutor

abstract class TestBase {

    private static final String ANY_HEADERS_KEY = 'jdhdkjdaladsjsahads983271619218%%#^#&&*@'
    protected static final Map<String,String> ANY_HEADERS = [(ANY_HEADERS_KEY): 'x']

    ResponseResolver resolver = new ResponseResolver(loadRules())

    static loadRules() {
        return ScriptExecutor.processAll('src/test/groovy/su/funk/diesel/rules4test')
    }

    void resolveAndVerify(RequestInfo req, ResponseInfo exp) {
        def act = resolver.resolve(req)
        assert act.statusCode == exp.statusCode
        if (!exp.headers.containsKey(ANY_HEADERS_KEY)) {
            assert act.headers == exp.headers
        }
        assert act.body == exp.body
    }

    void resolveAndVerify404(RequestInfo req) {
        def act = resolver.resolve(req)
        assert act.statusCode == 404
    }

    static RequestInfo req(String method, String path, Map<String,String> params = null, HttpHeaders headers = null, byte[] body = null) {
        new RequestInfo(method: method, path: path, params: params, headers: headers, body: body)
    }

    static ResponseInfo resp(int status, Map<String,String> headers = null, def body = null) {
        if (body instanceof String) {
            body = body.getBytes('utf-8')
        }
        HttpHeaders hh = new HttpHeaders()
        headers.each {key,val-> hh.add(key, val)}
        new ResponseInfo(statusCode: status, headers: hh, body: body)
    }

}
