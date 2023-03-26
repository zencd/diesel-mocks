package mockey.util

import mockey.runtime.RequestInfo

import java.util.function.Predicate

class ParamPredicate {
    String name
    Predicate<String> predicate

    boolean test(RequestInfo requestInfo) {

    }
}
