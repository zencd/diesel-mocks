package mockey.util

import java.util.function.Predicate

class HeaderPredicate {
    String key
    Predicate<String> predicate
}
