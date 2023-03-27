package su.funk.diesel.dsl

import su.funk.diesel.predicate.HeaderPredicate
import su.funk.diesel.predicate.ParamPredicate
import su.funk.diesel.predicate.Predicates
import su.funk.diesel.model.RuleModel

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
        aRequestProperty()
        methodImpl(method)
        pathImpl(path)
    }
    void method(String method) {
        aRequestProperty()
        methodImpl(method)
    }
    private void methodImpl(String method) {
        model.request.method = method
    }
    void path(String path) {
        aRequestProperty()
        pathImpl(path)
    }
    private void pathImpl(String path) {
        model.request.path = path
    }
    void header(String name, String value) {
        if (responseStarted) {
            model.response.headers.add(name, value)
        } else {
            def hp = new HeaderPredicate(name: name, predicate: Predicates.eq(value))
            model.request.headers.add(hp)
        }
    }
    void header(String name, Predicate<String> predicate) {
        aRequestProperty()
        def hp = new HeaderPredicate(name: name, predicate: predicate)
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
    void text(String text) {
        aResponseProperty()
        model.response.body = text
        model.response.setTextPlain()
    }
    void json(String text) {
        aResponseProperty()
        model.response.body = text
        model.response.setContentTypeJson()
    }
    void json(Closure maker) {
        aResponseProperty()
        model.response.body = maker
        model.response.setContentTypeJson()
    }
    void body(Predicate predicate) {
        aResponseProperty()
    }
    //void jsonFilter(String text) {
    //    model.jsonMatch = text
    //}
    void param(String name, String value) {
        aRequestProperty()
        param(name, Predicates.eq(value))
    }
    void param(String name, Predicate<String> predicate) {
        aRequestProperty()
        def pp = new ParamPredicate(name, predicate)
        model.request.paramPredicates.add(pp)
    }

    private void aRequestProperty() {
        assert !this.responseStarted : "response declaration already started"
    }
    private void aResponseProperty() {
        assert this.responseStarted : "response declaration has not started yet"
    }
}