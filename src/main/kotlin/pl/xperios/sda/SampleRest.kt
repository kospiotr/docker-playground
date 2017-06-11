package pl.xperios.sda

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleRest {


    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }

    @RequestMapping("/world")
    fun world(): String {
        return "world2"
    }

}
