import static mockey.Predicates.*
import static mockey.dsl.Specs.service

service {
    path "/af/users"
    name "USERS"
    swagger "http://swagger.com/users.yaml"

    rule {
        request {
            line "GET /user/ross"
            header "ns-client-ip", eqi("100")
        }
        response {
            status 200
            header "x-hello", "hello"
            json '{"name":"Привет"}'
            json(['name': 'A customized answer for Ross'])
        }
    }

    rule {
        request {
            line "GET /user/{name}"
        }
        response {
            status 200
            header "x-hello", "hello"
            json '{"name":"Привет"}'
        }
    }

    /*
    rule {
        line "GET", "/user/{name}"
        header "x-incoming", "hello"

        status 200
        header "x-outgoing", "hello"
        json '{"name":"Привет"}'
        json {
            """{"name":"Привет ${System.nanoTime()}"}"""
        }
        //+foo "x-hello", "hello"
        //headers["x-hello"] = "hello"
    }
    */
}
