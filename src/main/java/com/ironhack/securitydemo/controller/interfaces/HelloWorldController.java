package com.ironhack.securitydemo.controller.interfaces;

import com.ironhack.securitydemo.controller.dto.JustNameDTO;
import com.ironhack.securitydemo.security.CustomUserDetails;

public interface HelloWorldController {
    String helloWorld();
    String helloName(String name);
    String helloPost(JustNameDTO justNameDTO);
    String helloUser(CustomUserDetails userDetails);


}
