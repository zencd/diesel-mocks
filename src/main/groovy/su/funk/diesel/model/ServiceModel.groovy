package su.funk.diesel.model

import java.util.function.Predicate

class ServiceModel {
    String path
    Predicate<String> pathPredicate
    String name
    String swaggerUrl
    List<RuleModel> rules = []
}
