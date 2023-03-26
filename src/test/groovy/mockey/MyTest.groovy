package mockey


import org.junit.Test

class MyTest extends TestBase {

    //@Deprecated
    //@Test
    //void test_1() {
    //    def req = req('GET', '/test1/users/joe')
    //    def resp = resolver.resolve(req)
    //    assert resp.headers['x-outgoing'] == ['hello']
    //    assert resp.headers['content-type'] == ['application/json']
    //    assert resp.statusCode == 200
    //    assert new String(resp.body, 'utf-8') == '{"name":"Привет Joe"}'
    //}

    @Test
    void test_2() {
        def req = req('GET', '/test1/users/joe')
        def resp = resp(
                200,
                ['x-outgoing': 'hello', 'content-type': 'application/json'],
                '{"name":"Привет Joe"}')
        resolveAndVerify(req, resp)
    }
}
