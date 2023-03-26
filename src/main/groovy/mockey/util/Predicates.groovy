package mockey.util

import java.util.function.Predicate

class Predicates {

    static Predicate<String> eq(String value) {
        return new Predicate<String>() {
            boolean test(String s) {
                return s == value
            }
        }
    }

    static Predicate<String> eqi(String value) {
        return new Predicate<String>() {
            boolean test(String s) {
                return s.equalsIgnoreCase(value)
            }
        }
    }

    static Predicate<String> regex(String value) {}

    static Predicate<String> contains(String value) {}

    static Predicate partialMatch(String matcher) {}

    static Predicate containsJson(String matcher) {}

    static Predicate containsJson(Map<String,Object> matcher) {}
}
