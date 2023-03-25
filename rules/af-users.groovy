import static mockey.dsl.Specs.service
import static mockey.Predicates.*

service {
    path "/af/users"
    name "USERS"
    swagger "http://swagger.com/users.yaml"

    rule {
        line "GET", "/users/joe"
        header "x-incoming", eq("hello")
        responseBeginsHere 200
        header "x-outgoing", "hello"
        json { """{"name":"Привет Joe ${System.nanoTime()}"}""" }
    }

    rule {
        line "GET", "/users/{name}"
        status 200
        header "x-outgoing", "hello"
        json '{"name":"Привет generic"}'
    }
}
