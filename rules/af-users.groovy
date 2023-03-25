import static mockey.dsl.Specs.service
import static mockey.Predicates.*

service {
    path "/af/users"
    name "USERS"
    swagger "http://swagger.com/users.yaml"

    rule {
        line "GET", "/user/joe"
        header "x-incoming", eq("hello")

        status 200
        header "x-outgoing", "hello"
        json '{"name":"Привет"}'
        //json {
        //    """{"name":"Привет ${System.nanoTime()}"}"""
        //}
    }

    rule {
        line "GET", "/user/{name}"
        header "x-incoming", "hello"
        status 200
        header "x-outgoing", "hello"
        json '{"name":"Привет"}'
    }
}
