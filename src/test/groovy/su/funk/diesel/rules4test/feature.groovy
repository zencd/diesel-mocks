package su.funk.diesel.rules4test

import static su.funk.diesel.dsl.Specs.service
import static su.funk.diesel.predicate.Predicates.*

service {
    path "/test1"
    name "USERS"
    swagger "http://swagger.com/users.yaml"

    rule {
        line "GET", "/jsonRespWithString"
        RESPONSE_BEGINS 200
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
        RESPONSE_BEGINS 200
        header "x-outgoing", "hello"
        text 'responseHeader'
    }

    //rule {
    //    method 'GET'
    //    path '/path'
    //    responseBeginsHere 200
    //    text''
    //}
}
