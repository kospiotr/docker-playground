package pl.xperios.sda

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleRest {

    @RequestMapping("/")
    fun index(): String {
        return "Hi there stranger"
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "helloooooooooo"
    }

    @GetMapping("/hello1")
    fun hello1(): String {
        return "helloooooooooo1"
    }

    @RequestMapping("/world")
    fun world(): String {
        return "world2"
    }

}
