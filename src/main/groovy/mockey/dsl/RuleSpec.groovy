package mockey.dsl

import mockey.HeaderPredicate
import mockey.Predicates
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
        method(method)
        path(path)
    }
    void method(String method) {
        model.request.method = method
    }
    void path(String path) {
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
    void status(int code) {
        assert !this.responseStarted : "response declaration already started"
        model.response.statusCode = code
        this.responseStarted = true
    }
    void json(String text) {
        model.response.body = text
    }
    void json(Closure maker) {
        model.response.body = maker
    }
}