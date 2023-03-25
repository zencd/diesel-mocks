package mockey

import java.util.function.Predicate

class HeaderPredicate {
    String key
    Predicate<String> predicate
}
