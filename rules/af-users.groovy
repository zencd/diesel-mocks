import static mockey.dsl.Specs.service
import static mockey.Predicates.*

service {
    path "/af/users"
    name "USERS"
    swagger "http://swagger.com/users.yaml"

    rule {
        //line "GET", "/users/joe"
        method "GET"
        path "/users/joe"
        header "x-incoming", eqi("hello")
        responseBeginsHere 200
        header "x-outgoing", "hello"
        json { """{"name":"Привет Joe ${System.nanoTime()}"}""" }
    }

    rule {
        line "GET", "/users/{name}"
        RESPONSE_BEGINS 200
        header "x-outgoing", "hello"
        json '{"name":"Привет generic"}'
    }

    rule {
        line "POST", "/users"
        param "a", "b"
        jsonMatch '{"ip":"1.1.1.1"}'
        RESPONSE_BEGINS 200
        json '{"msg":"User added"}'
    }
}
