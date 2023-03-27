package su.funk.diesel.rules4test

import static su.funk.diesel.dsl.Specs.service

service {
    path "/test2"
    name "USERS"

    rule {
        line "GET", "/users"
        RESPONSE_BEGINS 200
        text "list users"
    }

    rule {
        line "GET", "/users/joe"
        RESPONSE_BEGINS 200
        text "exact match"
    }

    rule {
        line "GET", "/users/{name}/sub"
        RESPONSE_BEGINS 200
        text "in-path variable followed by smth else"
    }

    rule {
        line "GET", "/users/{name}"
        RESPONSE_BEGINS 200
        text "in-path variable"
    }

    rule {
        line "GET", "/wildcard/*"
        RESPONSE_BEGINS 200
        text "wildcard in path"
    }
}
