package su.funk.diesel.rules4test

import static su.funk.diesel.dsl.Specs.service
import static su.funk.diesel.predicate.Predicates.*

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
        responseBeginsHere 200
        text "paramCheck"
    }

    rule {
        line "GET", "/paramCheckWithPredicate"
        param "a", eq("b")
        responseBeginsHere 200
        text "paramCheckWithPredicate"
    }

    rule {
        line "GET", "/responseHeader"
        responseBeginsHere 200
        header "x-outgoing", "hello"
        text 'responseHeader'
    }

    rule {
        line "GET", "/statusCode500"
        responseBeginsHere 500
        text 'statusCode500'
    }

    //rule {
    //    method 'GET'
    //    path '/path'
    //    responseBeginsHere 200
    //    text''
    //}
}
