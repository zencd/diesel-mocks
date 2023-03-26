package mockey.util

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    void 'pathPatternToRegex'() {
        def res = Utils.pathPatternToRegex('/users/{name}/phones/{id}')
        assertEquals('/users/[^/]+/phones/[^/]+', res)
        assertTrue('/users/111/phones/222'.matches(res))
    }
}