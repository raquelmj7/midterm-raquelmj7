package com.ironhack.bankingsystem.controller.impl;

import com.ironhack.bankingsystem.controller.dto.JustNameDTO;
import com.ironhack.bankingsystem.controller.interfaces.HelloWorldController;
import com.ironhack.bankingsystem.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldControllerImpl implements HelloWorldController {
    @GetMapping("/hello-world")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld() {
        return "Hello World :D";
    }

    @GetMapping("/hello/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String helloName(@PathVariable(name="name") String name) {
        return "Hello " + name;
    }

    @PostMapping("/hello-post")
    @ResponseStatus(HttpStatus.OK)
    public String helloPost(@RequestBody JustNameDTO justNameDTO) {
        return "Hello " + justNameDTO.getName();
    }

    @GetMapping("/hello-user")
    @ResponseStatus(HttpStatus.OK)
    public String helloUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return "Hello " + userDetails.getUser().getUsername();
    }


}
