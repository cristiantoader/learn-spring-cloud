package com.ctoader.learn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetServiceController {

    @GetMapping("/{person}")
    public String greet(@PathVariable String person) {
        return String.format("Hello %s!", person);
    }
}
