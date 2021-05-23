package com.bill.kotlinwebsocket.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DispatchController {


    @GetMapping("")
    fun redirectToMain(): String{
        return "main";
    }
}