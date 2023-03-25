package mockey.dsl

import mockey.HeaderPredicate
import mockey.Predicates
import mockey.model.RuleModel

import java.util.function.Predicate

class RuleSpec {
    private responseStarted = false
    private RuleModel model = new RuleModel()

    void line(String method, String path) {
        model.request.method = method
        model.request.path = path
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