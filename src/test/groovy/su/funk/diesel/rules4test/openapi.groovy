package su.funk.diesel.rules4test

import static su.funk.diesel.dsl.Specs.service

service {
    path "/petstore"
    name "PETSTORE"
    //swagger "http://swagger.com/petstore.yaml"
    swagger "petstore3.yaml"
}
