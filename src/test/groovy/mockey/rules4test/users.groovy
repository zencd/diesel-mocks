package mockey.rules4test

import static mockey.dsl.Specs.service

service {
    path "/test1"
    name "USERS"
    swagger "http://swagger.com/users.yaml"

    rule {
        line "GET", "/users/joe"
        responseBeginsHere 200
        header "x-outgoing", "hello"
        json '{"name":"Привет Joe"}'
    }

    rule {
        line "GET", "/users/{name}"
        RESPONSE_BEGINS 200
        header "x-outgoing", "hello"
        json '{"name":"Привет generic"}'
    }

    rule {
        line "GET", "/paramCheck"
        param "a", "b"
        RESPONSE_BEGINS 200
        text "paramCheck"
    }
}
