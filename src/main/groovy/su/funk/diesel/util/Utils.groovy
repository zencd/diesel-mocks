package su.funk.diesel.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders

import java.util.function.Function
import java.util.regex.Pattern
import java.util.stream.Collectors

class Utils {

    private static def METHOD_AND_PATH_REGEX = Pattern.compile("\\s*(\\w+)\\s+(.+)")

    public static final String MATCH_ALL = "{*path}"

    static List<String> parseMethodAndPath(String methodAndPath) {
        def m = METHOD_AND_PATH_REGEX.matcher(methodAndPath)
        if (!m.matches()) {
            throw new BadRuleException("Illegal methodAndPath: $methodAndPath")
        }
        return [m.group(1), m.group(2)]
    }

    static File normalize(File file) {
        return file.toPath().toAbsolutePath().normalize().toFile()
    }

    static HttpHeaders httpHeaders(HttpServletRequest httpRequest) {
        return Collections.list(httpRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(httpRequest.getHeaders(h)),
                        (oldValue, newValue) -> newValue,
                        HttpHeaders::new
                ));
    }

    static var regex = Pattern.compile("(\\{[a-zA-Z0-9]+\\})")

    static pathPatternToRegex(String path) {
        // convert '/users/{name}' to '/users/.+'
        String result = path
        result = result.replaceAll('\\*', '.*')
        result = result.replaceAll(regex, "[^/]+")
        return result
    }

    static boolean isUrl(String url) {
        return url.startsWith('http://') || url.startsWith('https://')
    }
}
