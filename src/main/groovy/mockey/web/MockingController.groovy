package mockey.web

import groovy.util.logging.Slf4j
import jakarta.servlet.http.HttpServletRequest
import mockey.runtime.RequestInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Slf4j
@Controller
class MockingController {

    private static final String MATCH_ALL = "{*path}"

    @Autowired
    private SpringResponseResolver responseResolver

    @RequestMapping(value = MATCH_ALL, method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity onGet(HttpServletRequest httpRequest, InputStream inputStream) {
        return processRequest(httpRequest, inputStream)
    }

    @RequestMapping(value = MATCH_ALL, method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity onPost(HttpServletRequest httpRequest, InputStream inputStream) {
        return processRequest(httpRequest, inputStream)
    }

    @RequestMapping(value = MATCH_ALL, method = RequestMethod.DELETE)
    @ResponseBody
    ResponseEntity onDelete(HttpServletRequest httpRequest, InputStream inputStream) {
        return processRequest(httpRequest, inputStream)
    }

    private ResponseEntity processRequest(HttpServletRequest httpRequest, InputStream inputStream) {
        def reqInfo = RequestInfo.collect(httpRequest, inputStream)
        return responseResolver.resolve(reqInfo).toResponseEntity()
    }
}
