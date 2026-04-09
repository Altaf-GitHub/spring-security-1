package com.erdinfotech.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

 /*   @GetMapping("/welcome")
    public String m1(){
        return "welcome";
    }

    @GetMapping("/greet")
    public String m2(){
        return "greet";
    }

    @GetMapping("/public/contact")
    public String m3(){
        return "contact";
    }

    @GetMapping("/public/about")
    public String m4(){
        return "about";
    }*/

    @GetMapping("/admin/home")
    public String m1(){
        return "This is Admin home page";
    }

    @GetMapping("/manager/home")
    public String m2(){
        return "This is Manager home page";
    }

    @GetMapping("/student/home")
    public String m3(){
        return "This is Student home page";
    }

    @GetMapping("/public/contact")
    public String m4(){
        return "Please call us on +918452020203";
    }

    @GetMapping("/public/about")
    public String m5(){
        return "ERD Infotech Software Training institute mumbai";
    }
}
