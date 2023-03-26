package mockey.rules4test

import static mockey.dsl.Specs.service
import static mockey.predicate.Predicates.*

service {
    path "/test1"
    name "USERS"
    swagger "http://swagger.com/users.yaml"

    rule {
        line "GET", "/jsonRespWithString"
        responseBeginsHere 200
        json '"jsonRespWithString"'
    }

    rule {
        line "GET", "/paramCheck"
        param "a", "b"
        RESPONSE_BEGINS 200
        text "paramCheck"
    }

    rule {
        line "GET", "/paramCheckWithPredicate"
        param "a", eq("b")
        RESPONSE_BEGINS 200
        text "paramCheckWithPredicate"
    }

    rule {
        line "GET", "/responseHeader"
        responseBeginsHere 200
        header "x-outgoing", "hello"
        text 'responseHeader'
    }
}
