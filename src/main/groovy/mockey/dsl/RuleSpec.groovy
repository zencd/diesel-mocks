package mockey.dsl

import mockey.HeaderPredicate
import mockey.Predicates
import mockey.model.RuleModel

import java.util.function.Predicate

class RuleSpec {
    private responseStarted = false
    private RuleModel rule = new RuleModel()

    void line(String method, String path) {
        rule.request.method = method
        rule.request.path = path
    }
    void header(String name, String value) {
        if (responseStarted) {
            rule.response.headers.add(name, value)
        } else {
            def hp = new HeaderPredicate(key: name, predicate: Predicates.eq(value))
            rule.request.headers.add(hp)
        }
    }
    void header(String name, Predicate<String> predicate) {
        assert !this.responseStarted : "response declaration started already"
        def hp = new HeaderPredicate(key: name, predicate: predicate)
        rule.request.headers.add(hp)
    }
    void status(int code) {
        assert !this.responseStarted : "response declaration already started"
        rule.response.statusCode = code
        this.responseStarted = true
    }
    void json(String text) {
        rule.response.body = text
    }
    void json(Closure maker) {
        rule.response.body = maker
    }
}