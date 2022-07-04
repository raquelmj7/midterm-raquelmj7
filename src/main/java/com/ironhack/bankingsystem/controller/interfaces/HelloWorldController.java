package com.ironhack.bankingsystem.controller.interfaces;

import com.ironhack.bankingsystem.controller.dto.JustNameDTO;
import com.ironhack.bankingsystem.security.CustomUserDetails;

public interface HelloWorldController {
    String helloWorld();
    String helloName(String name);
    String helloPost(JustNameDTO justNameDTO);
    String helloUser(CustomUserDetails userDetails);


}
