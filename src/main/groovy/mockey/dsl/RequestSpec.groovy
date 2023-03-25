package mockey.dsl

import mockey.BadRuleException

import java.util.function.Predicate
import java.util.regex.Pattern

class RequestSpec {

    private static def METHOD_AND_PATH_REGEX = Pattern.compile("\\s*(\\w+)\\s+(.+)")

    private String method = "GET"
    private String path

    void line(String methodAndPath) {
        def m = METHOD_AND_PATH_REGEX.matcher(methodAndPath)
        if (!m.matches()) {
            throw new BadRuleException("Illegal line($methodAndPath)")
        }
        this.method = m.group(1)
        this.path = m.group(2)
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