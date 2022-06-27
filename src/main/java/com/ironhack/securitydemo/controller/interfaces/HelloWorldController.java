package com.ironhack.securitydemo.controller.interfaces;

import com.ironhack.securitydemo.controller.dto.JustNameDTO;

public interface HelloWorldController {
    String helloWorld();
    String helloName(String name);
    String helloPost(JustNameDTO justNameDTO);


}
