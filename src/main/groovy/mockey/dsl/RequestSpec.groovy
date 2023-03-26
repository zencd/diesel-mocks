package mockey.dsl


import mockey.util.Utils

import java.util.function.Predicate

@Deprecated
class RequestSpec {

    private String method = "GET"
    private String path

    void line(String methodAndPath) {
        def parsed = Utils.parseMethodAndPath(methodAndPath)
        this.method = parsed[0]
        this.path = parsed[1]
    }
    void method(String name) {
        this.method = name
    }
    void path(String path) {
        this.path = path
    }
    void function(String method, String path) {}
    void response(String body) {}
    void response(int sc, String body) {}
    void header(String name, String value) {}
    void header(String name, Predicate<Boolean> cl) {}
    void headerContains(String name, String value = "") {}
    void headerRegex(String name, String value = "") {}
}