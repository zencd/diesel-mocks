package mockey.rules4test

import static mockey.dsl.Specs.service

service {
    path "/test2"
    name "USERS"

    rule {
        line "GET", "/users"
        responseBeginsHere 200
        text "list users"
    }

    rule {
        line "GET", "/users/joe"
        responseBeginsHere 200
        text "get concrete user"
    }

    rule {
        line "GET", "/users/{name}"
        responseBeginsHere 200
        text "get a wildcard user"
    }
}
