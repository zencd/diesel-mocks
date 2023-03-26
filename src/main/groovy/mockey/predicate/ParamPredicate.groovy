package mockey.predicate

import groovy.transform.TupleConstructor
import mockey.runtime.RequestInfo

import java.util.function.Predicate

@TupleConstructor
class ParamPredicate {
    String name
    Predicate<String> predicate

    boolean test(RequestInfo requestInfo) {

    }
}
