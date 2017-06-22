package pl.xperios.sda

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleRest {

    @RequestMapping("/")
    fun index(): String {
        return "Hi there stranger, hosted from test1 branch updated"
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "helloooooooooo"
    }

    @RequestMapping("/world")
    fun world(): String {
        return "world2"
    }

}
