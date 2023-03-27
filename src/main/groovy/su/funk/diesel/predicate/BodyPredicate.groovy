package su.funk.diesel.predicate

import groovy.transform.TupleConstructor

import java.util.function.Predicate

@TupleConstructor(includeFields = true)
class BodyPredicate {
    final Predicate<String> predicate
}
