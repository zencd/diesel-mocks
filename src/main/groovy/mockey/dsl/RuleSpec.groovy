package mockey.dsl

import java.util.function.Predicate

class RuleSpec {
    private MetaSpec meta
    private RequestSpec request
    private ResponseSpec response

    def meta(@DelegatesTo(MetaSpec) Closure cl) {
        assert !request : "One `meta` block allowed"
        def spec = new MetaSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        this.meta = spec
        return spec
    }
    def when(@DelegatesTo(RequestSpec) Closure cl) {
        request(cl)
    }
    def then(@DelegatesTo(ResponseSpec) Closure cl) {
        response(cl)
    }
    def request(@DelegatesTo(RequestSpec) Closure cl) {
        assert !request : "One `request` block allowed"
        def spec = new RequestSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        this.request = spec
        return spec
    }
    def response(@DelegatesTo(ResponseSpec) Closure cl) {
        assert !response : "One `response` block allowed"
        def spec = new ResponseSpec()
        def code = cl.rehydrate(spec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
        this.response = spec
        return spec
    }
    void line(String value) {}
    void line(String method, String path) {}
    void header(String name, String value) {}
    void header(String name, Predicate<Boolean> cl) {}
    void headers() {}
    void addHeader(String name, String value) {}
    void path(String path) {}
    void function(String method, String path) {}
    void response(int sc, String body) {}
    //def json(String body) {
    //    println "rule json: $body"
    //}
    def comment(String text) {}
    //void header(String name, String value) {}
    def response(int status) {}
    def responds(int status) {}
    def status(int code) {}
    def json(String text) {}
    def json(Closure maker) {}

    static class Foo {
        def plus(int value) {
            println "Foo + called with $value"
        }
        def positive() {
            println "Foo positive called"
            return this
        }
        def call(String name1, String value1) {
            println "Foo call called with $name1 $value1"
        }
    }
    //def foo(int i) {}
    Foo foo = new Foo()
    Map<String,String> headers = [:]

    static class Resp {
        def call(int status) {
            println "Resp.call $status"
        }
        def rightShift(int status) {

        }
    }
    Resp resp = new Resp()
}