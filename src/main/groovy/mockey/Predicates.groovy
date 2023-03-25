package mockey

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
}
