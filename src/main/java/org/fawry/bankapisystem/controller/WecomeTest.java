package org.fawry.bankapisystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bank/user")
public class WecomeTest {

    @GetMapping
    String welcome(){
        return "hello";
    }
}
