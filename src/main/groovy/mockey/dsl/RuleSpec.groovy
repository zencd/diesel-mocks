package mockey.dsl

import mockey.util.HeaderPredicate
import mockey.util.Predicates
import mockey.model.RuleModel

import java.util.function.Predicate

class RuleSpec {
    private ServiceSpec serviceSpec
    private responseStarted = false
    private RuleModel model

    RuleSpec(ServiceSpec serviceSpec) {
        this.serviceSpec = serviceSpec
        this.model = new RuleModel(serviceModel: serviceSpec.model)
    }

    void line(String method, String path) {
        methodImpl(method)
        pathImpl(path)
    }
    void method(String method) {
        methodImpl(method)
    }
    private void methodImpl(String method) {
        model.request.method = method
    }
    void path(String path) {
        pathImpl(path)
    }
    private void pathImpl(String path) {
        model.request.path = path
        model.request.pathPredicate = Predicates.eq(path)
    }
    void path(Predicate<String> pathPredicate) {
        model.request.pathPredicate = pathPredicate
    }
    void header(String name, String value) {
        if (responseStarted) {
            model.response.headers.add(name, value)
        } else {
            def hp = new HeaderPredicate(key: name, predicate: Predicates.eq(value))
            model.request.headers.add(hp)
        }
    }
    void header(String name, Predicate<String> predicate) {
        assert !this.responseStarted : "response declaration started already"
        def hp = new HeaderPredicate(key: name, predicate: predicate)
        model.request.headers.add(hp)
    }
    void responseBeginsHere(int code) {
        status(code)
    }
    void RESPONSE_BEGINS(int code) {
        status(code)
    }
    void status(int code) {
        assert !this.responseStarted : "response declaration already started"
        model.response.statusCode = code
        this.responseStarted = true
    }
    void json(String text) {
        model.response.body = text
        model.response.setContentTypeJson()
    }
    void json(Closure maker) {
        model.response.body = maker
        model.response.setContentTypeJson()
    }
    void body(Predicate predicate) {
    }
    //void jsonFilter(String text) {
    //    model.jsonMatch = text
    //}
    void param(String name, String value) {
    }
    void param(String name, Predicate<String> predicate) {
    }
}