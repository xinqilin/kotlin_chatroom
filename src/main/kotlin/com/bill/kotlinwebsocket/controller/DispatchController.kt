package com.bill.kotlinwebsocket.controller

import com.bill.kotlinwebsocket.common.Logging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DispatchController : Logging{


    @GetMapping("")
    fun redirectToMain(): String{
        log().info("I am log")
        return "Hello";
    }
}