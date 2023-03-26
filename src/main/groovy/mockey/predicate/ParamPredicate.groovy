package mockey.predicate

import groovy.transform.TupleConstructor

import java.util.function.Predicate

@TupleConstructor(includeFields = true)
class ParamPredicate {
    private final String name
    private final Predicate<String> predicate
}
