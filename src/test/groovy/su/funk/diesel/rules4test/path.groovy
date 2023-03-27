package su.funk.diesel.rules4test

import static su.funk.diesel.dsl.Specs.service

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
        text "exact match"
    }

    rule {
        line "GET", "/users/{name}/sub"
        responseBeginsHere 200
        text "in-path variable followed by smth else"
    }

    rule {
        line "GET", "/users/{name}"
        responseBeginsHere 200
        text "in-path variable"
    }

    rule {
        line "GET", "/wildcard/*"
        responseBeginsHere 200
        text "wildcard in path"
    }
}
